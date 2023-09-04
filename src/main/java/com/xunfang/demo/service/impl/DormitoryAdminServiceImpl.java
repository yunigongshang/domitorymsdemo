package com.xunfang.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xunfang.demo.entity.DormitoryAdmin;
import com.xunfang.demo.mapper.DormitoryAdminMapper;
import com.xunfang.demo.ruleform.RuleForm;
import com.xunfang.demo.ruleform.SearchForm;
import com.xunfang.demo.service.DormitoryAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunfang.demo.vo.PageVo;
import com.xunfang.demo.vo.ResultVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2023-05-15
 */
@Service
public class DormitoryAdminServiceImpl extends ServiceImpl<DormitoryAdminMapper, DormitoryAdmin> implements DormitoryAdminService {
    @Resource
    private DormitoryAdminMapper dormitoryAdminMapper;
    @Override
    public ResultVO login(RuleForm ruleForm){
        QueryWrapper<DormitoryAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",ruleForm.getUsername());
        DormitoryAdmin dormitoryAdmin = this.dormitoryAdminMapper.selectOne(queryWrapper);
        ResultVO resultVO = new ResultVO();
        if (dormitoryAdmin==null){
            resultVO.setCode(-1);
        }else if (!dormitoryAdmin.getPassword().equals(ruleForm.getPassword())){
            resultVO.setCode(-2);
        }else {
            resultVO.setCode(0);
            resultVO.setData(dormitoryAdmin);
        }
        return resultVO;
    }
    @Override
    public PageVo list(Integer page, Integer size){
        Page<DormitoryAdmin> dormitoryAdminPage = new Page<>(page,size);
        Page<DormitoryAdmin> resultPage = this.dormitoryAdminMapper.
                selectPage(dormitoryAdminPage,null);
        PageVo pageVo = new PageVo();
        pageVo.setTotal(resultPage.getTotal());
        pageVo.setData(resultPage.getRecords());
        return pageVo;
    }
    @Override
    public PageVo search (SearchForm searchForm ){
        Page<DormitoryAdmin> dormitoryAdminPage =
                new Page<>(searchForm.getPage(),searchForm.getSize ());
        Page<DormitoryAdmin> resultPage = null;
        if ( searchForm.getValue().equals ("")){
            resultPage = this.dormitoryAdminMapper .
                    selectPage ( dormitoryAdminPage,null);
        } else {
            QueryWrapper<DormitoryAdmin> queryWrapper = new QueryWrapper<>();
            queryWrapper.like (searchForm.getKey(), searchForm.getValue());
            resultPage = this.dormitoryAdminMapper.
                    selectPage ( dormitoryAdminPage , queryWrapper);
        }
        PageVo pageVo = new PageVo();
        pageVo.setTotal(resultPage.getTotal());
        pageVo.setData(resultPage.getRecords());
        return pageVo;
    }
}
