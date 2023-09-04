package com.xunfang.demo.service;

import com.xunfang.demo.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xunfang.demo.ruleform.SearchForm;
import com.xunfang.demo.ruleform.StudentForm;
import com.xunfang.demo.vo.PageVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2023-05-15
 */
public interface StudentService extends IService<Student> {

    Boolean saveStudent(Student student);
    PageVo list(Integer page,Integer size);

    PageVo search(SearchForm searchForm);

    Boolean update(StudentForm studentForm);


    boolean deleteById(Integer id);
}
