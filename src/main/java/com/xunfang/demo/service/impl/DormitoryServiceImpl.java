package com.xunfang.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xunfang.demo.entity.Building;
import com.xunfang.demo.entity.Dormitory;
import com.xunfang.demo.mapper.BuildingMapper;
import com.xunfang.demo.mapper.DormitoryMapper;
import com.xunfang.demo.ruleform.SearchForm;
import com.xunfang.demo.service.DormitoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunfang.demo.vo.DormitoryVO;
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
public class DormitoryServiceImpl extends ServiceImpl<DormitoryMapper, Dormitory> implements DormitoryService {
    @Resource
    private DormitoryMapper dormitoryMapper;
    @Resource
    private BuildingMapper buildingMapper;

    @Override
    public PageVo dormitory(Page<Dormitory> resultPage){
        List<Dormitory> buildingList = resultPage.getRecords();
        ArrayList<DormitoryVO> dormitoryVOList = new ArrayList<>();
        for (Dormitory building : buildingList){
            DormitoryVO dormitoryVO = new DormitoryVO();
            BeanUtils.copyProperties(building,dormitoryVO);
            Building dormitory = this.buildingMapper
                    .selectById(building.getBuildingId());
            dormitoryVO.setBuildingName(dormitory.getName());
            dormitoryVOList.add(dormitoryVO);
        }
        PageVo pageVo = new PageVo();
        pageVo.setTotal(resultPage.getTotal());
        pageVo.setData(dormitoryVOList);
        return pageVo;
    }
    @Override
    public PageVo list(Integer page, Integer size){
        Page<Dormitory> dormitoryPage = new Page<>(page,size);
        Page<Dormitory> resultPage= this.dormitoryMapper
                .selectPage(dormitoryPage,null);
        return dormitory(resultPage);
    }
    @Override
    public PageVo search (SearchForm searchForm ){
        Page<Dormitory> dormitoryAdminPage =
                new Page<>(searchForm.getPage(),searchForm.getSize ());
        Page<Dormitory> resultPage = null;
        if ( searchForm.getValue().equals ("")){
            resultPage = this.dormitoryMapper .
                    selectPage ( dormitoryAdminPage,null);
        } else {
            QueryWrapper<Dormitory> queryWrapper = new QueryWrapper<>();
            queryWrapper.like (searchForm.getKey(), searchForm.getValue());
            resultPage = this.dormitoryMapper.
                    selectPage ( dormitoryAdminPage , queryWrapper);
        }
        return dormitory(resultPage);
    }
}
