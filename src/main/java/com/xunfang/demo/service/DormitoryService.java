package com.xunfang.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xunfang.demo.entity.Dormitory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xunfang.demo.ruleform.SearchForm;
import com.xunfang.demo.vo.PageVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2023-05-15
 */
public interface DormitoryService extends IService<Dormitory> {
//    PageVo dormitory(Page<Dormitory> resultPage);

    PageVo dormitory(Page<Dormitory> resultPage);

    PageVo list(Integer page, Integer size);

    PageVo search(SearchForm searchForm);
}
