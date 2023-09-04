package com.xunfang.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xunfang.demo.vo.ResultVO;
import com.xunfang.demo.entity.SystemAdmin;
import com.xunfang.demo.mapper.SystemAdminMapper;
import com.xunfang.demo.ruleform.RuleForm;
import com.xunfang.demo.service.SystemAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class SystemAdminServiceImpl extends ServiceImpl<SystemAdminMapper, SystemAdmin> implements SystemAdminService {
    @Resource
    private SystemAdminMapper systemAdminMapper;
    @Override
    public ResultVO login(RuleForm ruleForm) {
        QueryWrapper<SystemAdmin>queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",ruleForm.getUsername());
        SystemAdmin systemAdmin = this.systemAdminMapper.selectOne(queryWrapper);
        ResultVO resultVO = new ResultVO();
        if (systemAdmin==null){
            resultVO.setCode(-1);
        }else if (!systemAdmin.getPassword().equals(ruleForm.getPassword())){
            resultVO.setCode(-2);
        }else {
            resultVO.setCode(0);
            resultVO.setData(systemAdmin);
        }
            return resultVO;
    }

}
