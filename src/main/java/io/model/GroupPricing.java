package io.model;

import java.math.BigDecimal;

public class GroupPricing {
    Long id;
    Long groupId;
    Long groupModelId;
    Long groupPricingOptionId;
    Long groupOptionValueId;
    BigDecimal price;
    BigDecimal quantity;
    BigDecimal affiliatePrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupModelId() {
        return groupModelId;
    }

    public void setGroupModelId(Long groupModelId) {
        this.groupModelId = groupModelId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getGroupPricingOptionId() {
        return groupPricingOptionId;
    }

    public void setGroupPricingOptionId(Long groupPricingOptionId) {
        this.groupPricingOptionId = groupPricingOptionId;
    }

    public Long getGroupOptionValueId() {
        return groupOptionValueId;
    }

    public void setGroupOptionValueId(Long groupOptionValueId) {
        this.groupOptionValueId = groupOptionValueId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAffiliatePrice() {
        return affiliatePrice;
    }

    public void setAffiliatePrice(BigDecimal affiliatePrice) {
        this.affiliatePrice = affiliatePrice;
    }
}
