package com.lcl.galaxy.springmvc.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String name;
    private String password;
    private List<OrderInfoDto> orderInfoDtos;
}
