package com.xunfang.demo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xunfang.demo.entity.Dormitory;
import com.xunfang.demo.entity.Moveout;
import com.xunfang.demo.entity.Student;
import com.xunfang.demo.mapper.DormitoryMapper;
import com.xunfang.demo.mapper.MoveoutMapper;
import com.xunfang.demo.mapper.StudentMapper;
import com.xunfang.demo.service.MoveoutService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunfang.demo.vo.MoveoutVO;
import com.xunfang.demo.vo.PageVo;
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
}
