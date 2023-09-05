package com.xunfang.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xunfang.demo.entity.Building;
import com.xunfang.demo.entity.Dormitory;
import com.xunfang.demo.entity.DormitoryAdmin;
import com.xunfang.demo.entity.Student;
import com.xunfang.demo.ruleform.SearchForm;
import com.xunfang.demo.service.BuildingService;
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
@RequestMapping("/building")
public class BuildingController {
    @Autowired
    public BuildingService buildingService;

    /**
     * 添加管理员
     * @param building
     * @return
     */
    @PostMapping("/save")
    public ResultVO save(@RequestBody Building building){
        Boolean save = this.buildingService.save(building);
        if (!save)return ResultUntil.fail();
        return ResultUntil.success(null);
    }
    @GetMapping("/list/{page}/{size}")
    public ResultVO list(@PathVariable("page")Integer page,
                         @PathVariable("size")Integer size){
        PageVo pageVo = this.buildingService.list(page,size);
        return ResultUntil.success(pageVo);
    }
    @GetMapping("/search")
    public ResultVO search(SearchForm searchForm){
        PageVo pageVo = this.buildingService.search(searchForm);
        return ResultUntil.success(pageVo);
    }
    @DeleteMapping("/deleteById/{id}")
    public ResultVO deleteById(@PathVariable("id")Integer id){
        Boolean delete = this.buildingService.removeById(id);
        if (!delete)return ResultUntil.fail();
        return ResultUntil.success(null);
    }

    @GetMapping("/list")
    public ResultVO list() {
        List<Building> buildingList = this.buildingService.list();
        return ResultUntil.success(buildingList);
    }
}

