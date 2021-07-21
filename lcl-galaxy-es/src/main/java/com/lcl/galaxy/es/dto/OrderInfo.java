package com.lcl.galaxy.es.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderInfo {
    private long orderId;
    private String orderName;
    private String content;
}
