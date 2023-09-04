package com.xunfang.demo.controller;


import com.xunfang.demo.entity.DormitoryAdmin;
import com.xunfang.demo.entity.Student;
import com.xunfang.demo.ruleform.SearchForm;
import com.xunfang.demo.ruleform.StudentForm;
import com.xunfang.demo.service.StudentService;
import com.xunfang.demo.until.ResultUntil;
import com.xunfang.demo.vo.PageVo;
import com.xunfang.demo.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2023-05-15
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    public StudentService studentService;
    @PostMapping("/save")
    public ResultVO save(@RequestBody Student student){
    Boolean save = this.studentService.saveStudent(student);
    if (!save)return ResultUntil.fail();
    return ResultUntil.success(null);
    }
    @GetMapping("/list/{page}/{size}")
    public ResultVO list(@PathVariable("page")Integer page,
                         @PathVariable("size")Integer size){
        PageVo pageVo = this.studentService.list(page,size);
        return ResultUntil.success(pageVo);
    }
    @GetMapping("/search")
    public ResultVO search(SearchForm searchForm){
        PageVo pageVo = this.studentService.search(searchForm);
        return ResultUntil.success(pageVo);
    }
    @GetMapping("/findById/{id}")
    public  ResultVO findById(@PathVariable("id") Integer id){
        Student student=this.studentService.getById(id);
        StudentForm studentForm = new StudentForm();
        BeanUtils.copyProperties(student,studentForm);
        studentForm.setOldDormitoryId(student.getDormitoryId());
        return ResultUntil.success(studentForm);
    }
    @PutMapping("/update")
    public ResultVO update(@RequestBody StudentForm studentForm){
        Boolean update = this.studentService.update(studentForm);
        if (!update)return ResultUntil.fail();
        return ResultUntil.success(null);
    }
    @DeleteMapping("/deleteById/{id}")
    public ResultVO deleteById(@PathVariable("id") Integer id){
        boolean remove=this.studentService.removeById(id);
        if (!remove)return ResultUntil.fail();
        return ResultUntil.success(null);
    }
}

