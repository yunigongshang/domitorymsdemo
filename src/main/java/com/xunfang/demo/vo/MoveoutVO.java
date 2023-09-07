package com.xunfang.demo.vo;

import lombok.Data;

@Data
public class MoveoutVO {
    private Integer id;
    private String studentName;
    private String dormitoryName;

    private String studentId;

    private String dormitoryId;

    private String reason;

    private String createDate;
}
