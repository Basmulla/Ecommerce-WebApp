package com.ecommerce.dto;

import com.ecommerce.entity.Orders;
import com.ecommerce.entity.OrderDetails;
import com.ecommerce.entity.Shipping;
import java.util.List;

public class CheckoutResponse {

    private Orders order;
    private List<OrderDetails> items;
    private Shipping shipping;

    public CheckoutResponse() {}

    public CheckoutResponse(Orders order, List<OrderDetails> items, Shipping shipping) {
        this.order = order;
        this.items = items;
        this.shipping = shipping;
    }

    public Orders getOrder() {
        return order;
    }
    public void setOrder(Orders order) {
        this.order = order;
    }

    public List<OrderDetails> getItems() {
        return items;
    }
    public void setItems(List<OrderDetails> items) {
        this.items = items;
    }

    public Shipping getShipping() {
        return shipping;
    }
    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }
}
