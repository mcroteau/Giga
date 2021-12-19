package io.model;

import java.math.BigDecimal;

public class GroupPricing {
    Long id;
    Long groupId;
    Long groupPricingOptionId;
    Long groupOptionValueId;
    BigDecimal price;
    BigDecimal quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
