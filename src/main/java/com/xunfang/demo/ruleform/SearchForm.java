package com.xunfang.demo.ruleform;

import lombok.Data;

import javax.swing.border.EtchedBorder;
@Data
public class SearchForm {
    private String key;
    private String value;
    private Integer page;
    private Integer size;
}
