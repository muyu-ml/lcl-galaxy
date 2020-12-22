package com.lcl.galaxy.design.pattern.prototype;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private static final long serialVersion = 1L;
    private String orderId;

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}
