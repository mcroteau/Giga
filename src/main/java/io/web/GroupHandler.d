package io.web;

import io.service.GroupService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import qio.annotate.HttpHandler;
import qio.annotate.Inject;
import qio.annotate.Variable;
import qio.annotate.verbs.Get;
import qio.annotate.verbs.Post;
import qio.model.web.ResponseData;

import java.io.IOException;

@HttpHandler
public class GroupHandler {

    @Inject
    GroupService groupService;

    @Get("/{{business}}/item_groups/{{id}}")
    public String getItem(HttpServletRequest req,
                          ResponseData data,
                          @Variable String business,
                          @Variable Long id){
        return groupService.getItemGroup(id, business, data, req);
    }

    @Get("/{{business}}/item_groups/{{categoryId}}/{{id}}")
    public String getItem(HttpServletRequest req,
                          ResponseData data,
                          @Variable String business,
                          @Variable Long categoryId,
                          @Variable Long id){
        return groupService.getItemGroupCatalog(id, categoryId, business, data, req);
    }

    @Get("/items/new/{{businessId}}")
    public String configure(ResponseData data,
                            @Variable Long businessId){
        return groupService.create(businessId, data);
    }

    @Get("/items/{{businessId}}")
    public String list(ResponseData data,
                       @Variable Long businessId){
        return groupService.list(businessId, data);
    }

    @Get("/items/grid/{{businessId}}")
    public String grid(ResponseData data,
                       @Variable Long businessId){
        return groupService.grid(businessId, data);
    }

    @Post("/items/save/{{businessId}}")
    public String save(HttpServletRequest req,
                        @Variable Long businessId) throws IOException, ServletException {
        return groupService.save(businessId, req);
    }


    @Post("/items/update/{{businessId}}/{{id}}")
    public String update(HttpServletRequest req,
                         ResponseData data,
                         @Variable Long businessId,
                         @Variable Long id) throws IOException, ServletException{
        return groupService.update(id, businessId, false, data, req);
    }

    @Post("/item_groups/delete/{{businessId}}/{{id}}")
    public String delete(ResponseData data,
                         @Variable Long businessId,
                         @Variable Long id){
        return groupService.delete(id, businessId, data);
    }


//
//    @Get("/item_groups/options/{{businessId}}/{{id}}")
//    public String options(ResponseData data,
//                           @Variable Long businessId,
//                           @Variable Long id) throws Exception {
//        return groupService.options(id, businessId, data);
//    }
//
//    @Get("/items/options/save/{{businessId}}/{{id}}")
//    public String getOptions(ResponseData data,
//                          @Variable Long businessId,
//                          @Variable Long id) throws Exception {
//        return itemService.options(id, businessId, data);
//    }
//
//    @Post("/items/options/save/{{businessId}}/{{id}}")
//    public String saveOption(HttpServletRequest req,
//                             ResponseData data,
//                             @Variable Long businessId,
//                             @Variable Long id){
//        return itemService.saveOption(id, businessId, data, req);
//    }
//
//    @Post("/items/options/delete/{{businessId}}/{{optionId}}/{{id}}")
//    public String deleteOption(HttpServletRequest req,
//                             ResponseData data,
//                             @Variable Long businessId,
//                             @Variable Long optionId,
//                             @Variable Long id){
//        return itemService.deleteOption(id, optionId, businessId, data, req);
//    }
//
//    @Get("/items/options/delete/{{businessId}}/{{optionId}}/{{id}}")
//    public String deleteOption(ResponseData data,
//                               @Variable Long businessId,
//                               @Variable Long id){
//        return itemService.options(id, businessId, data);
//    }
//
//    @Post("/items/options/values/save/{{businessId}}/{{id}}")
//    public String saveValue(HttpServletRequest req,
//                            ResponseData data,
//                             @Variable Long businessId,
//                             @Variable Long id){
//        return itemService.saveValue(id, businessId, data, req);
//    }
//
//
//    @Get("/items/options/values/save/{{businessId}}/{{id}}")
//    public String getValues(ResponseData data,
//                            @Variable Long businessId,
//                            @Variable Long id){
//        return itemService.options(id, businessId, data);
//    }
//
//    @Post("/items/options/values/delete/{{businessId}}/{{valueId}}/{{id}}")
//    public String deleteValue(HttpServletRequest req,
//                             ResponseData data,
//                             @Variable Long businessId,
//                             @Variable Long valueId,
//                             @Variable Long id){
//        return itemService.deleteValue(id, valueId, businessId, data, req);
//    }
//
//    @Get("/items/options/values/delete/{{businessId}}/{{valueId}}/{{id}}")
//    public String deleteValue(ResponseData data,
//                              @Variable Long businessId,
//                              @Variable Long id){
//        return itemService.options(id, businessId, data);
//    }

}
