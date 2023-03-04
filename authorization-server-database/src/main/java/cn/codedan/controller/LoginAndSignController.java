package cn.codedan.controller;

import cn.codedan.facade.OauthClientFacade;
import cn.codedan.facade.UserDetailFacade;
import cn.codedan.model.dto.requset.OauthClientInfoReqDTO;
import cn.codedan.model.dto.requset.UserInfoReqDTO;
import cn.codedan.model.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: LoginAndSignController
 * @Description: 授权服务器的登录与注册接口
 * @Author: CodeDan
 * @Date: 2023/3/4 10:31
 * @Version: 1.0.0
 **/
@RestController
@RequestMapping("/authorization")
public class LoginAndSignController {

    @Autowired
    private OauthClientFacade oauthClientFacade;
    @Autowired
    private UserDetailFacade userDetailFacade;

    @PostMapping("/sign/client")
    public String signClient(@RequestBody OauthClientInfoReqDTO clientInfoReqDTO){
        oauthClientFacade.add(clientInfoReqDTO);
        return "success";
    }

    @PostMapping("/sign/user")
    public String signClient(@RequestBody UserInfoReqDTO userInfoReqDTO){
        userDetailFacade.add(userInfoReqDTO);
        return "success";
    }



}
