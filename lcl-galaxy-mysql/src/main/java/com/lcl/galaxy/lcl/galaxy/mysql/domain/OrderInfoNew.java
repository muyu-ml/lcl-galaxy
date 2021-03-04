package com.lcl.galaxy.lcl.galaxy.mysql.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderInfoNew {
    private long id;
    private long orderId;
    private long venderId;
}
