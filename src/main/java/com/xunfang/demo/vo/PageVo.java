package com.xunfang.demo.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class PageVo {
   private Object data;
   private Long total;
}
