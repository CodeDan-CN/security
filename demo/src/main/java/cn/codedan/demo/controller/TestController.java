package cn.codedan.demo.controller;

import cn.codedan.demo.model.dto.ResponseReusltDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TestController
 * @Description: 测试接口
 * @Author: CodeDan
 * @Date: 2023/2/5 12:14
 * @Version: 1.0.0
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/hello")
    @PreAuthorize("hasAuthority('test')")
    private ResponseReusltDTO test(){
        return ResponseReusltDTO.ok();
    }
}
