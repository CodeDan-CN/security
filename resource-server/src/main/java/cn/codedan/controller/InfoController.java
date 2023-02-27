package cn.codedan.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: InfoController
 * @Description: 测试的资源Controller
 * @Author: CodeDan
 * @Date: 2023/2/26 16:48
 * @Version: 1.0.0
 **/
@RestController
@RequestMapping("/info")
public class InfoController {

    @GetMapping("/get")
    @PreAuthorize("hasRole('root')")
    public String get(){
        return "success";
    }
}
