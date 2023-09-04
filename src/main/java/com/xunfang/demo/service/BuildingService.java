package com.xunfang.demo.service;

import com.xunfang.demo.entity.Building;
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
public interface BuildingService extends IService<Building> {

    PageVo list(Integer page, Integer size);
    PageVo search(SearchForm searchForm);
}
