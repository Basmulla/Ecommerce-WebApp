package com.ecommerce.dto;

import java.util.List;

public class CheckoutRequest {

    private Long customerId;
    private List<CartItem> items;

    public Long getCustomerId() {
        return customerId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
}
