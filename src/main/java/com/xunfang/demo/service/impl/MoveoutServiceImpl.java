package com.xunfang.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xunfang.demo.entity.Dormitory;
import com.xunfang.demo.entity.Moveout;
import com.xunfang.demo.entity.Student;
import com.xunfang.demo.mapper.DormitoryMapper;
import com.xunfang.demo.mapper.MoveoutMapper;
import com.xunfang.demo.mapper.StudentMapper;
import com.xunfang.demo.ruleform.SearchForm;
import com.xunfang.demo.service.MoveoutService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunfang.demo.until.CommonUntil;
import com.xunfang.demo.vo.MoveoutVO;
import com.xunfang.demo.vo.PageVo;
import com.xunfang.demo.vo.StudentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
@Service
public class MoveoutServiceImpl extends ServiceImpl<MoveoutMapper, Moveout> implements MoveoutService {
    @Resource
    private MoveoutMapper moveoutMapper;
    @Resource
    private StudentMapper studentMapper;
    @Resource
    public DormitoryMapper dormitoryMapper;
    @Override
    public PageVo list(Integer page, Integer size) {
        Page<Moveout> studentPage = new Page<>(page,size);
        Page<Moveout> resultPage = this.moveoutMapper.
                selectPage(studentPage,null);
        return moveout(resultPage);
    }

    private PageVo moveout(Page<Moveout>resultPage){
        List<Moveout>studentList = resultPage.getRecords();
        ArrayList<MoveoutVO> moveoutVOArrayList = new ArrayList<>();
        for (Moveout moveout :studentList){
            MoveoutVO moveoutVO = new MoveoutVO();
            BeanUtils.copyProperties(moveout,moveoutVO);
            Student student = this.studentMapper.
                    selectById(moveout.getStudentId());
            Dormitory dormitory=this.dormitoryMapper.
                    selectById(moveout.getDormitoryId());
            moveoutVO.setStudentName(student.getName());
            moveoutVO.setDormitoryName(dormitory.getName());
            moveoutVOArrayList.add(moveoutVO);
        }
        PageVo pageVo = new PageVo();
        pageVo.setData(moveoutVOArrayList);
        pageVo.setTotal(resultPage.getTotal());
        return pageVo;
    }
    @Override
    public PageVo studentlist(Integer page, Integer size) {
        QueryWrapper<Student> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("state","入住");
        Page<Student> studentPage = new Page<>(page,size);
        Page<Student> resultPage = this.studentMapper.
                selectPage(studentPage,queryWrapper);
        return student(resultPage);
    }
    private PageVo student(Page<Student>resultPage){
        List<Student>studentList = resultPage.getRecords();
        ArrayList<StudentVO> studentVOList = new ArrayList<>();
        for (Student student :studentList){
                StudentVO studentVO = new StudentVO();
                BeanUtils.copyProperties(student, studentVO);
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
    @Override
    public Moveout updatemov(Integer id, String value){
        Student student1 = this.studentMapper.selectById(id);
        student1.setState("迁出");
        this.studentMapper.updateById(student1);
        Moveout moveout=new Moveout();
        moveout.setStudentId(String.valueOf(student1.getId()));
        moveout.setDormitoryId(String.valueOf(student1.getDormitoryId()));
        moveout.setReason(value);
        moveout.setCreateDate(CommonUntil.createDate());
        Dormitory dormitory =this.dormitoryMapper.selectById(student1.getDormitoryId());
        dormitory.setAvailable(dormitory.getAvailable()+1);
        this.dormitoryMapper.updateById(dormitory);
        return moveout;
    }
    @Override
    public PageVo search(SearchForm searchForm){
        Page<Student>studentPage = new Page<>
                (searchForm.getPage(),searchForm.getSize());
        Page<Student>resultPage =null;
        if (searchForm.getValue().equals("")){
            QueryWrapper<Student> queryWrapper =new QueryWrapper<>();
            queryWrapper.eq("state","入住");
            resultPage = this.studentMapper
                    .selectPage(studentPage,queryWrapper);
        }else {
            QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
            queryWrapper.like (searchForm.getKey(), searchForm.getValue());
            resultPage = this.studentMapper.
                    selectPage ( studentPage , queryWrapper);
        }
        return student(resultPage);
    }
}
