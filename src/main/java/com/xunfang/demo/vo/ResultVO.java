package com.xunfang.demo.vo;

import com.xunfang.demo.entity.SystemAdmin;
import lombok.Data;

@Data
public class ResultVO<T> {
    private Integer code;
    private T data;

}
