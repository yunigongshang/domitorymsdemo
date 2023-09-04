package com.xunfang.demo.service;

import com.xunfang.demo.vo.ResultVO;
import com.xunfang.demo.entity.SystemAdmin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xunfang.demo.ruleform.RuleForm;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2023-05-15
 */
public interface SystemAdminService extends IService<SystemAdmin> {

    ResultVO login(RuleForm ruleForm);
}
