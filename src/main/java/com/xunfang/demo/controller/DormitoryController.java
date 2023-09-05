package com.xunfang.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xunfang.demo.entity.Dormitory;
import com.xunfang.demo.entity.DormitoryAdmin;
import com.xunfang.demo.ruleform.SearchForm;
import com.xunfang.demo.service.DormitoryService;
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
    /**
     * 添加宿舍
     * **/
    @PostMapping("/save")
        public ResultVO save(@RequestBody Dormitory dormitory){
        dormitory.setAvailable(dormitory.getType());
        Boolean save = this.dormitoryService.save(dormitory);
        if (!save)return ResultUntil.fail();
        return ResultUntil.success(null);
    }

    @GetMapping("/list/{page}/{size}")
    public ResultVO list(@PathVariable("page")Integer page,
                         @PathVariable("size")Integer size){
        PageVo pageVo = this.dormitoryService.list(page,size);
        return ResultUntil.success(pageVo);
    }
    @GetMapping("/search")
    public ResultVO search(SearchForm searchForm){
        PageVo pageVo = this.dormitoryService.search(searchForm);
        return ResultUntil.success(pageVo);
    }
    @DeleteMapping("/deleteById/{id}")
    public ResultVO deleteById(@PathVariable("id") Integer id){
        boolean remove=this.dormitoryService.removeById(id);
        if (!remove)return ResultUntil.fail();
        return ResultUntil.success(null);
    }
    @GetMapping("/findById/{id}")
    public  ResultVO findById(@PathVariable("id") Integer id){
        Dormitory admin=this.dormitoryService.getById(id);
        return ResultUntil.success(admin);
    }
    @PutMapping("/update")
    public ResultVO update(@RequestBody Dormitory dormitory){
        boolean update = this.dormitoryService.updateById(dormitory);
        if (!update)return ResultUntil.fail();
        return ResultUntil.success(null);
    }
}

