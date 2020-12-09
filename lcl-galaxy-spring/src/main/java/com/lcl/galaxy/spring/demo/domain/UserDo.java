package com.lcl.galaxy.spring.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDo {

    private String id;
    private String name;

}
