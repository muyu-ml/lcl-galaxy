package com.lcl.galaxy.mybatis.common.vo;

import lombok.*;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryVo {
    private String sex;
    private List<String> addressList;
}
