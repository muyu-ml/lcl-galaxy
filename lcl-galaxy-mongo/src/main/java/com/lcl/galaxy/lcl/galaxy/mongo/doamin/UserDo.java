package com.lcl.galaxy.lcl.galaxy.mongo.doamin;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDo {

    private String name;
    private int age;
    private String city;

}
