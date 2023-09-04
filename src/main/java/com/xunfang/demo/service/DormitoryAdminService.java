package com.xunfang.demo.service;

import com.xunfang.demo.entity.DormitoryAdmin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xunfang.demo.ruleform.RuleForm;
import com.xunfang.demo.ruleform.SearchForm;
import com.xunfang.demo.vo.PageVo;
import com.xunfang.demo.vo.ResultVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2023-05-15
 */
public interface DormitoryAdminService extends IService<DormitoryAdmin> {
    ResultVO login(RuleForm ruleForm);
    PageVo list(Integer page, Integer size);
    PageVo search(SearchForm searchForm);
}
