package com.xunfang.demo.controller;


import com.xunfang.demo.vo.ResultVO;
import com.xunfang.demo.ruleform.RuleForm;
import com.xunfang.demo.service.SystemAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2023-05-15
 */
@RestController
@RequestMapping("/systemAdmin")
public class SystemAdminController {
    @Autowired
    private SystemAdminService systemAdminService;
    @GetMapping("/login")
    public ResultVO login(RuleForm ruleForm){
        ResultVO resultVO = this.systemAdminService.login(ruleForm);
        return resultVO;
    }
}



