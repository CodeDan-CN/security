package cn.codedan.demo.controller;

import cn.codedan.demo.facade.UserDetailsFacade;
import cn.codedan.demo.model.dto.ResponseReusltDTO;
import cn.codedan.demo.model.dto.UserReqDTO;
import cn.codedan.demo.service.LoginService;
import cn.codedan.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: LoginController
 * @Description: 登录Controller
 * @Author: CodeDan
 * @Date: 2022/12/13 14:56
 * @Version: 1.0.0
 **/
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserDetailsFacade userDetailsFacade;

    @PostMapping("/login")
    public ResponseReusltDTO login(@RequestBody UserReqDTO user){
        return loginService.login(user);
    }

    @PostMapping("/sign")
    public ResponseReusltDTO sign(@RequestBody UserReqDTO user){
        userDetailsFacade.sign(user);
        return ResponseReusltDTO.ok();
    }

}
