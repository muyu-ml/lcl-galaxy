package com.lcl.galaxy.spring.demo.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDo {

    private String id;
    private String name;

}
