package com.xunfang.demo.service;

import com.xunfang.demo.entity.Moveout;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xunfang.demo.vo.PageVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2023-05-15
 */
public interface MoveoutService extends IService<Moveout> {

    PageVo list(Integer page, Integer size);
}
