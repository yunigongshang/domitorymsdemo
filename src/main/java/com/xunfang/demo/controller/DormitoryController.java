package com.xunfang.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xunfang.demo.entity.Dormitory;
import com.xunfang.demo.service.DormitoryService;
import com.xunfang.demo.until.ResultUntil;
import com.xunfang.demo.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2023-05-15
 */
@RestController
@RequestMapping("/dormitory")
public class DormitoryController {
    @Autowired
    private DormitoryService dormitoryService;
    @GetMapping("/availableList")
        public ResultVO availableList(){
         QueryWrapper<Dormitory> queryWrapper =new QueryWrapper<>();
         queryWrapper.gt("available",0);
        List<Dormitory> dormitoryList = this.dormitoryService.list(queryWrapper);
        return ResultUntil.success(dormitoryList);
    }


}

