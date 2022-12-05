package com.lcl.galaxy.mybatis.simple.common.vo;

import com.lcl.galaxy.mybatis.simple.common.domain.UserDo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrameQueryVo {
    private long id;
    private UserDo userDo;
    private String sex;
}
