package com.xunfang.demo.until;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUntil {
    public static String createDate(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }
}
