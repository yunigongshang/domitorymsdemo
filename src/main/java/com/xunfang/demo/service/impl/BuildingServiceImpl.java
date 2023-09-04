package com.xunfang.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xunfang.demo.entity.Building;
import com.xunfang.demo.entity.DormitoryAdmin;
import com.xunfang.demo.entity.Student;
import com.xunfang.demo.mapper.BuildingMapper;
import com.xunfang.demo.mapper.DormitoryAdminMapper;
import com.xunfang.demo.ruleform.SearchForm;
import com.xunfang.demo.service.BuildingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunfang.demo.vo.BuildingVO;
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
public class BuildingServiceImpl extends ServiceImpl<BuildingMapper, Building> implements BuildingService {
    @Resource
    public BuildingMapper buildingMapper;
    @Resource
    public DormitoryAdminMapper dormitoryAdminMapper;
    @Override
    public PageVo list(Integer page, Integer size) {
        Page<Building> buildingPage = new Page<>(page,size);
        Page<Building> resultPage= this.buildingMapper
                .selectPage(buildingPage,null);
        return building(resultPage);
    }
    private PageVo building(Page<Building>resultPage){
        List<Building>buildingList = resultPage.getRecords();
        ArrayList<BuildingVO> buildingVOList = new ArrayList<>();
        for (Building building : buildingList){
            BuildingVO buildingVO = new BuildingVO();
            BeanUtils.copyProperties(building,buildingVO);
            DormitoryAdmin dormitoryAdmin = this.dormitoryAdminMapper
                    .selectById(building.getAdminId());
            buildingVO.setAdminName(dormitoryAdmin.getName());
            buildingVOList.add(buildingVO);
        }
        PageVo pageVo = new PageVo();
        pageVo.setTotal(resultPage.getTotal());
        pageVo.setData(buildingList);
        return pageVo;
    }
    @Override
    public PageVo search(SearchForm searchForm) {
        Page<Building> buildingPage = new Page<Building>
                (searchForm.getPage(),searchForm.getSize());
        Page<Building> resultPage = null;
        if (searchForm.getValue().equals("")){
            resultPage = this.buildingMapper.selectPage(buildingPage,null);
        }else {
            QueryWrapper<Building> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(searchForm.getKey(),searchForm.getValue());
            resultPage = this.buildingMapper.selectPage(buildingPage,queryWrapper);
        }
        return building(resultPage);
    }

}
