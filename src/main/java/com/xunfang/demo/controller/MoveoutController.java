package com.xunfang.demo.controller;


import com.xunfang.demo.entity.Moveout;
import com.xunfang.demo.ruleform.SearchForm;
import com.xunfang.demo.service.MoveoutService;
import com.xunfang.demo.service.StudentService;
import com.xunfang.demo.until.ResultUntil;
import com.xunfang.demo.vo.PageVo;
import com.xunfang.demo.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2023-05-15
 */
@RestController
@RequestMapping("/moveout")
public class MoveoutController {
    @Autowired
    private MoveoutService moveoutService;
    @Autowired
    private StudentService studentService;
    @GetMapping("/list/{page}/{size}")
    public ResultVO list(@PathVariable("page")Integer page,
                         @PathVariable("size")Integer size){
        PageVo pageVo = this.moveoutService.studentlist(page,size);
        return ResultUntil.success(pageVo);
    }
    @GetMapping("moveoutList/{page}/{size}")
    public ResultVO moveoutList(@PathVariable("page")Integer page,
                                @PathVariable("size")Integer size) {
        PageVo pageVo = this.moveoutService.list(page, size);
        return ResultUntil.success(pageVo);
    }
    @PutMapping("/moveout/{id}/{value}")
    public ResultVO moveout(@PathVariable("id")Integer id,
                            @PathVariable("value")String value){
        Moveout moveout = this.moveoutService.updatemov(id, value);
        Boolean save=this.moveoutService.save(moveout);
        return ResultUntil.success(save);
    }
    @GetMapping("/search")
    public ResultVO search(SearchForm searchForm){
        PageVo pageVo = this.moveoutService.search(searchForm);
        return ResultUntil.success(pageVo);
    }
}

