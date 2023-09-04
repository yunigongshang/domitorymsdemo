package com.xunfang.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xunfang.demo.entity.Dormitory;
import com.xunfang.demo.entity.Student;
import com.xunfang.demo.mapper.DormitoryMapper;
import com.xunfang.demo.mapper.StudentMapper;
import com.xunfang.demo.ruleform.SearchForm;
import com.xunfang.demo.ruleform.StudentForm;
import com.xunfang.demo.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunfang.demo.until.CommonUntil;
import com.xunfang.demo.vo.PageVo;
import com.xunfang.demo.vo.StudentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2023-05-15
 */
@Transactional
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Resource
    public StudentMapper studentMapper;
    @Resource
    public DormitoryMapper dormitoryMapper;
    @Override
    public Boolean saveStudent(Student student) {
        student.setCreateDate(CommonUntil.createDate());
       int insert = this.studentMapper.insert(student);
       if (insert!=1)return false;
           Dormitory dormitory = this.dormitoryMapper
                   .selectById(student.getDormitoryId());
           if (dormitory.getAvailable()==0)return false;
           dormitory.setAvailable(dormitory.getAvailable()-1);
           int update = this.dormitoryMapper.updateById(dormitory);

        if (update!=1)return false;
        return true;
    }

    @Override
    public PageVo list(Integer page, Integer size) {
        Page<Student> studentPage = new Page<>(page,size);
        Page<Student> resultPage = this.studentMapper.
                selectPage(studentPage,null);
        return student(resultPage);
    }
    @Override
    public PageVo search(SearchForm searchForm){
        Page<Student>studentPage = new Page<>
                (searchForm.getPage(),searchForm.getPage());
        Page<Student>resultPage =null;
        if (searchForm.getValue().equals("")){
            resultPage = this.studentMapper
                    .selectPage(studentPage,null);
        }else {
            QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
            queryWrapper.like (searchForm.getKey(), searchForm.getValue());
            resultPage = this.studentMapper.
                    selectPage ( studentPage , queryWrapper);
        }
        return student(resultPage);
    }

    @Override
    public Boolean update(StudentForm studentForm) {
        Student student = new Student();
        BeanUtils.copyProperties(studentForm,student);
        int update = this.studentMapper.updateById(student);
        if (update!=1)return false;
        if (!studentForm.getDormitoryId().equals(studentForm.getOldDormitoryId())){
            try {
                this.dormitoryMapper.addAvailable(studentForm.getOldDormitoryId());
                this.dormitoryMapper.subAvailable(studentForm.getDormitoryId());
            }catch (Exception e){
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean deleteById(Integer id){
        Student student = this.studentMapper.selectById(id);
        try{
            Dormitory dormitory = this.dormitoryMapper
                    .selectById(student.getDormitoryId());
            if (dormitory.getType()>dormitory.getAvailable()){
                this.dormitoryMapper.addAvailable(dormitory.getId());
            }
        }catch (Exception e){
            return false;
        }
        int delete = this.studentMapper.deleteById(id);
        if (delete!=1)return false;
        return true;
    }

    private PageVo student(Page<Student>resultPage){
        List<Student>studentList = resultPage.getRecords();
        ArrayList<StudentVO> studentVOList = new ArrayList<>();
        for (Student student :studentList){
            StudentVO studentVO = new StudentVO();
            BeanUtils.copyProperties(student,studentVO);
            Dormitory dormitory = this.dormitoryMapper.
                    selectById(student.getDormitoryId());
            studentVO.setDormitoryName(dormitory.getName());
            studentVOList.add(studentVO);
        }
        PageVo pageVo = new PageVo();
        pageVo.setData(studentVOList);
        pageVo.setTotal(resultPage.getTotal());
        return pageVo;
    }
}
