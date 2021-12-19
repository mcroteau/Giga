package io.service;

import io.Giga;
import io.model.*;
import io.repo.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import qio.Qio;
import qio.annotate.Inject;
import qio.annotate.Service;
import qio.model.web.ResponseData;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {

    @Inject
    GroupRepo groupRepo;

    @Inject
    AssetRepo assetRepo;

    @Inject
    UserRepo userRepo;

    @Inject
    CategoryRepo categoryRepo;

    @Inject
    DesignRepo designRepo;

    @Inject
    BusinessRepo businessRepo;

    @Inject
    SiteService siteService;

    @Inject
    BusinessService businessService;

    @Inject
    SeaService seaService;

    @Inject
    AuthService authService;


    public String getItemGroup(Long id, String businessUri, ResponseData data, HttpServletRequest req) {
        Business business = businessRepo.get(businessUri);
        if(business == null){
            return "[redirect]/home";
        }

        Group group = groupRepo.get(id, business.getId());
        if(group == null){
            return "[redirect]/" + businessUri;
        }

        setData(id, data);

        data.set("group", group);
        data.set("business", business);
        data.set("request", req);
        data.set("siteService", siteService);

        return "/pages/group/index.jsp";
    }

    public String getGroupGroupCatalog(Long id, Long categoryId, String businessUri, ResponseData data, HttpServletRequest req) {
        System.out.println(id + " : " + categoryId + " : " + businessUri);
        Business business = businessRepo.get(businessUri);
        if(business == null){
            return "[redirect]/home";
        }

        Group group = groupRepo.get(id, business.getId());
        if(group == null){
            return "[redirect]/" + businessUri;
        }

        Category category = categoryRepo.get(categoryId);
        if(category == null){
            return "[redirect]/" + businessUri;
        }

        setData(id, data);

        data.set("group", group);
        data.set("category", category);
        data.set("business", business);
        data.set("request", req);
        data.set("siteService", siteService);

        return "/pages/group/index.jsp";
    }


    public String create(Long businessId, ResponseData data){
        if(!authService.isAuthenticated()){
            return "[redirect]/";
        }
        businessService.setData(businessId, data);

        List<Category> categories = categoryRepo.getListAll(businessId);
        if(categories.size() == 0){
            data.set("message", "You have to walk before you can run... you need to create a category before you can continue.");
            return "[redirect]/categories/new/" + businessId;
        }
        data.set("categories", categories);

        List<Design> designs = designRepo.getList(businessId);
        data.set("designs", designs);

        List<Asset> assets = assetRepo.getList(businessId);
        data.set("assets", assets);

        data.set("page", "/pages/group/new.jsp");
        return "/designs/auth.jsp";
    }


    public String save(Long businessId, HttpServletRequest req) throws IOException, ServletException {
        if(!authService.isAuthenticated()){
            return "[redirect]/";
        }

        User authUser = authService.getUser();

        Group group = (Group) Qio.get(req, Group.class);

        List<Part> fileParts = req.getParts()
                .stream()
                .filter(part -> "media".equals(part.getName()) && part.getSize() > 0)
                .collect(Collectors.toList());

        for (Part part : fileParts) {
            String original = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            InputStream is = part.getInputStream();
            String ext = Giga.getExt(original);
            String name = Giga.getString(9) + "." + ext;
            seaService.send(name, is);
            group.setImageUri(Giga.OCEAN_ENDPOINT + name);
        }

        if(group.getImageUri() == null){
            return "[redirect]/groups/new/" + businessId;
        }

        groupRepo.save(group);
        Group savedGroup = groupRepo.getSaved();

        String[] categories = req.getParameterValues("categories");
        System.out.println("a " + req.getParameterValues("categories") + " : " + categories);
        for(String id : categories){
            GroupCategory categoryGroup = new GroupCategory(savedGroup.getId(), Long.valueOf(id.trim()), businessId);
            categoryRepo.saveGroup(categoryGroup);
        }

        String permission = Giga.ITEM_MAINTENANCE + savedGroup.getId();
        userRepo.savePermission(authUser.getId(), permission);

        return "[redirect]/groups/" + savedGroup.getBusinessId();
    }

    public String list(Long businessId, ResponseData data){
        if(!authService.isAuthenticated()){
            return "[redirect]/";
        }
        businessService.setData(businessId, data);

        List<Group> groups = groupRepo.getList(businessId);
        data.set("groups", groups);
        data.set("title", "Active Groups");
        data.set("siteService", siteService);
        data.set("page", "/pages/group/list.jsp");
        return "/designs/auth.jsp";
    }


    public String getListInactive(Long businessId, ResponseData data) {
        if(!authService.isAuthenticated()){
            return "[redirect]/";
        }
        businessService.setData(businessId, data);

        List<Group> groups = groupRepo.getList(businessId, false);
        data.set("groups", groups);
        data.set("title", "Inactive Groups");
        data.set("siteService", siteService);
        data.set("page", "/pages/group/list.jsp");
        return "/designs/auth.jsp";
    }

    public String grid(Long businessId, ResponseData data) {
        if(!authService.isAuthenticated()){
            return "[redirect]/";
        }
        businessService.setData(businessId, data);

        List<Group> groups = groupRepo.getList(businessId);
        for(Group group : groups){
            List<Category> categories = new ArrayList<>();
            List<CategoryGroup> categoryGroups = categoryRepo.getCategoryGroups(group.getId());
            for(CategoryGroup categoryGroup : categoryGroups){
                Category category = categoryRepo.get(categoryGroup.getCategoryId());
                categories.add(category);
            }
            group.setCategories(categories);
        }
        List<Design> designs = designRepo.getList(businessId);
        List<Category> categories = categoryRepo.getListAll(businessId);
        data.set("designs", designs);
        data.set("categories", categories);
        data.set("groups", groups);
        data.set("siteService", siteService);
        data.set("page", "/pages/group/grid.jsp");
        return "/designs/auth.jsp";
    }

    public String edit(Long id, Long businessId, ResponseData data) {
        if(!authService.isAuthenticated()){
            return "[redirect]/";
        }

        String permission = Giga.ITEM_MAINTENANCE + id;
        if(!authService.isAdministrator() &&
                !authService.hasPermission(permission)){
            data.set("message", "Unauthorized to edit this group.");
            return "[redirect]/";
        }

        businessService.setData(businessId, data);

        Group group = groupRepo.get(id);

        data.set("group", group);

        List<Category> categories = categoryRepo.getListAll(businessId);
        data.set("categories", categories);

        List<CategoryGroup> categoryGroups = categoryRepo.getCategoryGroups(group.getId());
        List<Category> activeCategories = new ArrayList<>();
        for(CategoryGroup categoryGroup : categoryGroups){
            Category category = categoryRepo.get(categoryGroup.getCategoryId());
            activeCategories.add(category);
        }
        data.set("activeCategories", activeCategories);

        if(categoryGroups != null &&
                categoryGroups.size() > 0){
            CategoryGroup categoryGroup = categoryGroups.get(0);
            data.set("categoryId", categoryGroup.getCategoryId());
        }

        List<Design> designs = designRepo.getList(businessId);
        data.set("designs", designs);

        List<Asset> assets = assetRepo.getList(businessId);
        data.set("assets", assets);

        data.set("page", "/pages/group/edit.jsp");
        return "/designs/auth.jsp";
    }


    public String update(Long id, Long businessId, Boolean onGrid, ResponseData data, HttpServletRequest req) throws IOException, ServletException {
        if(!authService.isAuthenticated()){
            return "[redirect]/";
        }

        Business business = businessRepo.get(businessId);

        String permission = Giga.ITEM_MAINTENANCE + id;
        if(!authService.isAdministrator() &&
                !authService.hasPermission(permission)){
            data.set("message", "Unauthorized to edit this design.");
            return "[redirect]/";
        }

        Group group = (Group) Qio.get(req, Group.class);

        if(req.getParameter("media") != null){
            List<Part> fileParts = req.getParts()
                    .stream()
                    .filter(part -> "media".equals(part.getName()) && part.getSize() > 0)
                    .collect(Collectors.toList());

            for (Part part : fileParts) {
                String original = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                InputStream is = part.getInputStream();
                String ext = Giga.getExt(original);
                String name = Giga.getString(9) + "." + ext;
                seaService.send(name, is);
                group.setImageUri(Giga.OCEAN_ENDPOINT + name);
            }
        }

        categoryRepo.deleteCategoryGroups(id);

        String[] categories = req.getParameterValues("categories");
        if(categories != null) {
            System.out.println("a " + req.getParameterValues("categories") + " : " + categories);
            for (String categoryId : categories) {
                CategoryGroup categoryGroup = new CategoryGroup(group.getId(), Long.valueOf(categoryId.trim()), businessId);
                categoryRepo.saveGroup(categoryGroup);
            }
        }

        if(business.getAffiliate() != null &&
                business.getAffiliate() &&
                (group.getAffiliatePrice().compareTo(group.getPrice()) == 1)){
            data.set("message", "Your price may not be lower than the business owners. I get it, you want to buy their products at a lower price. You're a genius. ; )");
            if(onGrid){
                return "[redirect]/groups/grid/" + businessId;
            }
            return "[redirect]/groups/edit/" + businessId + "/" + id;
        }

        groupRepo.update(group);
        data.set("message", "Successfully updated group");

        if(onGrid){
            return "[redirect]/groups/grid/" + businessId;
        }
        return "[redirect]/groups/edit/" + businessId + "/" + id;
    }

    public String delete(Long id, Long businessId, ResponseData data) {
        if(!authService.isAuthenticated()){
            return "[redirect]/";
        }

        String permission = Giga.ITEM_MAINTENANCE + id;
        if(!authService.isAdministrator() &&
                !authService.hasPermission(permission)){
            data.set("message", "Unauthorized to delete this group.");
            return "[redirect]/";
        }

        categoryRepo.deleteCategoryGroups(id);
        List<GroupOption> groupOptions = groupRepo.getOptions(id);
        for(GroupOption groupOption : groupOptions){
            groupRepo.deleteValues(groupOption.getId());
        }
        groupRepo.deleteOptions(id);
        groupRepo.delete(id);
        data.set("message", "Successfully deleted group.");

        return "[redirect]/groups/" + businessId;
    }

    public String options(Long id, Long businessId, ResponseData data) {
        if(!authService.isAuthenticated()){
            return "[redirect]/";
        }

        String permission = Giga.ITEM_MAINTENANCE + id;
        if(!authService.isAdministrator() &&
                !authService.hasPermission(permission)){
            data.set("message", "Unauthorized to delete this group.");
            return "[redirect]/";
        }

        setData(id, data);
        businessService.setData(businessId, data);

        data.set("page", "/pages/group/options.jsp");
        return "/designs/auth.jsp";
    }

    public String saveOption(Long id, Long businessId, ResponseData data, HttpServletRequest req) {
        if(!authService.isAuthenticated()){
            return "[redirect]/";
        }

        String permission = Giga.ITEM_MAINTENANCE + id;
        if(!authService.isAdministrator() &&
                !authService.hasPermission(permission)){
            data.set("message", "Unauthorized to delete this group.");
            return "[redirect]/";
        }

        GroupOption groupOption = (GroupOption) Qio.get(req, GroupOption.class);
        groupRepo.saveOption(groupOption);

        return "[redirect]/groups/options/" + businessId + "/" + id;
    }


    public String deleteOption(Long id, Long optionId, Long businessId, ResponseData data, HttpServletRequest req) {
        if(!authService.isAuthenticated()){
            return "[redirect]/";
        }

        String permission = Giga.ITEM_MAINTENANCE + id;
        if(!authService.isAdministrator() &&
                !authService.hasPermission(permission)){
            data.set("message", "Unauthorized to delete this group.");
            return "[redirect]/";
        }

        System.out.println("delete option ");
        groupRepo.deleteValues(optionId);
        groupRepo.deleteOption(optionId);

        return "[redirect]/groups/options/" + businessId + "/" + id;
    }

    public String saveValue(Long id, Long businessId, ResponseData data, HttpServletRequest req) {
        if(!authService.isAuthenticated()){
            return "[redirect]/";
        }

        String permission = Giga.ITEM_MAINTENANCE + id;
        if(!authService.isAdministrator() &&
                !authService.hasPermission(permission)){
            data.set("message", "Unauthorized to delete this group.");
            return "[redirect]/";
        }

        OptionValue optionValue = (OptionValue) Qio.get(req, OptionValue.class);
        groupRepo.saveValue(optionValue);

        return "[redirect]/groups/options/" + businessId + "/" + id;
    }

    public String deleteValue(Long id, Long valueId, Long businessId, ResponseData data, HttpServletRequest req) {
        if(!authService.isAuthenticated()){
            return "[redirect]/";
        }

        String permission = Giga.ITEM_MAINTENANCE + id;
        if(!authService.isAdministrator() &&
                !authService.hasPermission(permission)){
            data.set("message", "Unauthorized to delete this group.");
            return "[redirect]/";
        }

        System.out.println("delete option value ");
        groupRepo.deleteValue(valueId);

        setData(id, data);
        businessService.setData(businessId, data);

        return "[redirect]/groups/options/" + businessId + "/" + id;
    }

    public void setData(Long id, ResponseData data){
        Group group = groupRepo.get(id);
        List<GroupOption> groupOptions = groupRepo.getOptions(group.getId());
        for(GroupOption groupOption : groupOptions){
            List<OptionValue> optionValues = groupRepo.getValues(groupOption.getId());
            groupOption.setOptionValues(optionValues);
        }
        data.set("group", group);
        data.set("groupOptions", groupOptions);
    }

}
