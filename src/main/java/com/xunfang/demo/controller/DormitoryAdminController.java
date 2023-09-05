package com.xunfang.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xunfang.demo.entity.Dormitory;
import com.xunfang.demo.entity.DormitoryAdmin;
import com.xunfang.demo.ruleform.RuleForm;
import com.xunfang.demo.ruleform.SearchForm;
import com.xunfang.demo.service.DormitoryAdminService;
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
@RequestMapping("/dormitoryAdmin")
public class DormitoryAdminController {
    @Autowired
    private DormitoryAdminService dormitoryAdminService;
    @GetMapping("/login")
    public ResultVO login(RuleForm ruleForm){
        ResultVO resultVO = this.dormitoryAdminService.login(ruleForm);
        return resultVO;
    }
    @PostMapping("/save")
    public ResultVO save(@RequestBody DormitoryAdmin dormitoryAdmin){
        boolean save = this.dormitoryAdminService.save(dormitoryAdmin);
        ResultVO resultVO = new ResultVO();
        if (save) {
            resultVO.setCode(0);
        }else {
            resultVO.setCode(-1);
        }
        return resultVO;
    }

    /**
     * 分页
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list/{page}/{size}")
    public ResultVO list(@PathVariable("page")Integer page,
                         @PathVariable("size")Integer size){
        PageVo pageVo = this.dormitoryAdminService.list(page,size);
        return ResultUntil.success(pageVo);
    }
    @GetMapping("/search")
    public ResultVO search(SearchForm searchForm){
        PageVo pageVo = this.dormitoryAdminService.search(searchForm);
        return ResultUntil.success(pageVo);
    }
    @GetMapping("/findById/{id}")
    public  ResultVO findById(@PathVariable("id") Integer id){
        DormitoryAdmin admin=this.dormitoryAdminService.getById(id);
        return ResultUntil.success(admin);
    }
    @PutMapping("/update")
    public ResultVO update(@RequestBody DormitoryAdmin dormitoryAdmin){
       boolean update = this.dormitoryAdminService.updateById(dormitoryAdmin);
        if (!update)return ResultUntil.fail();
        return ResultUntil.success(null);
    }
    @DeleteMapping("/deleteById/{id}")
    public ResultVO deleteById(@PathVariable("id") Integer id){
        boolean remove=this.dormitoryAdminService.removeById(id);
        if (!remove)return ResultUntil.fail();
        return ResultUntil.success(null);
    }
    @GetMapping("/list")
    public ResultVO list(){
        List<DormitoryAdmin> dormitoryAdminList = this.dormitoryAdminService.list();
        return ResultUntil.success(dormitoryAdminList);
    }
}

