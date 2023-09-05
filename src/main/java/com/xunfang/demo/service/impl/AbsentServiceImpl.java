package com.xunfang.demo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xunfang.demo.entity.*;
import com.xunfang.demo.mapper.*;
import com.xunfang.demo.service.AbsentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunfang.demo.vo.AbsentVO;
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
public class AbsentServiceImpl extends ServiceImpl<AbsentMapper, Absent> implements AbsentService {
    @Resource
    public AbsentMapper absentMapper;
    @Resource
    public BuildingMapper buildingMapper;
    @Resource
    public DormitoryMapper dormitoryMapper;
    @Resource
    public StudentMapper studentMapper;
    @Resource
    private DormitoryAdminMapper dormitoryAdminMapper;
    @Override
    public PageVo list(Integer page, Integer size) {
        Page<Absent> absentPage = new Page<>(page,size);
        Page<Absent> resultPage= this.absentMapper
                .selectPage(absentPage,null);
        return absent(resultPage);
    }
    private PageVo absent(Page<Absent>resultPage){
        List<Absent> absentList = resultPage.getRecords();
        ArrayList<AbsentVO> absentVOList = new ArrayList<>();
        for (Absent absent : absentList){
            AbsentVO absentVO = new AbsentVO();
            BeanUtils.copyProperties(absent,absentVO);
            Building building = this.buildingMapper
                    .selectById(absent.getBuildingId());
            absentVO.setBuildingName(building.getName());
            Dormitory dormitory = this.dormitoryMapper
                    .selectById(absent.getDormitoryId());
            absentVO.setDormitoryName(dormitory.getName());
            Student student = this.studentMapper
                    .selectById(absent.getStudentId());
            absentVO.setStudentName(student.getName());
            DormitoryAdmin dormitoryAdmin = this.dormitoryAdminMapper
                    .selectById(absent.getDormitoryAdminId());
            absentVO.setDormitoryAdminName(dormitoryAdmin.getName());
            absentVOList.add(absentVO);
        }
        PageVo pageVo = new PageVo();
        pageVo.setTotal(resultPage.getTotal());
        pageVo.setData(absentVOList);
        return pageVo;
    }
}

