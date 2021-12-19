package io.model;

public class GroupOptionValue {
    Long id;
    Long groupModelId;
    Long groupOptionId;
    Long groupPricingId;
    String value;

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

    public Long getGroupOptionId() {
        return groupOptionId;
    }

    public void setGroupOptionId(Long groupOptionId) {
        this.groupOptionId = groupOptionId;
    }

    public Long getGroupPricingId() {
        return groupPricingId;
    }

    public void setGroupPricingId(Long groupPricingId) {
        this.groupPricingId = groupPricingId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
