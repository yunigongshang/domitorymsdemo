package com.xunfang.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xunfang.demo.entity.Absent;
import com.xunfang.demo.entity.Building;
import com.xunfang.demo.entity.Dormitory;
import com.xunfang.demo.entity.Student;
import com.xunfang.demo.service.AbsentService;
import com.xunfang.demo.service.BuildingService;
import com.xunfang.demo.service.DormitoryService;
import com.xunfang.demo.service.StudentService;
import com.xunfang.demo.until.ResultUntil;
import com.xunfang.demo.vo.PageVo;
import com.xunfang.demo.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
@RequestMapping("/absent")
public class AbsentController {
    @Autowired
    public AbsentService absentService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private DormitoryService dormitoryService;
    @Autowired
    private StudentService studentService;
    @GetMapping("/list/{page}/{size}")
    public ResultVO list(@PathVariable("page")Integer page,
                         @PathVariable("size")Integer size){
        PageVo pageVo = this.absentService.list(page,size);
        return ResultUntil.success(pageVo);
    }
    @GetMapping("/buildingList")
    public ResultVO availableList(){
        QueryWrapper<Building> queryWrapper =new QueryWrapper<>();
        List<Building> dormitoryList = this.buildingService.list(queryWrapper);
        return ResultUntil.success(dormitoryList);
    }
    @GetMapping("/findDormitoryByBuildingId/{id}")
    public ResultVO findDormitoryByBuildingId(@PathVariable("id")Integer id){
        QueryWrapper<Dormitory> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("building_id",id);
        List<Dormitory> dormitoryList = this.dormitoryService.list(queryWrapper);
        return ResultUntil.success(dormitoryList);
    }
    @GetMapping("/findStudentByDormitoryId/{id}")
    public ResultVO findStudentByDormitoryId(@PathVariable("id")Integer id){
        QueryWrapper<Student> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("dormitory_id",id);
        List<Student> dormitoryList = this.studentService.list(queryWrapper);
        return ResultUntil.success(dormitoryList);
    }
    @PostMapping("/save")
    public ResultVO save(@RequestBody Absent absent){
        Boolean save=this.absentService.save(absent);
        if (!save)return ResultUntil.fail();
        return ResultUntil.success(null);
    }

}

