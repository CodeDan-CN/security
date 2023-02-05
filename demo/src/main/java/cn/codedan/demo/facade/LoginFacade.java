package cn.codedan.demo.facade;

import cn.codedan.demo.model.dto.ResponseReusltDTO;
import cn.codedan.demo.model.entity.LoginUser;
import cn.codedan.demo.model.dto.UserReqDTO;
import cn.codedan.demo.model.entity.UserInfo;
import cn.codedan.demo.service.LoginService;
import cn.codedan.demo.util.JwtUtils;
import cn.codedan.demo.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @ClassName: LoginServiceImpl
 * @Description: TODO
 * @Author: codedan
 * @Date: 2022/12/13 17:30
 * @Version: 1.0.0
 **/
@Service
public class LoginFacade implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache cache;

    @Override
    public ResponseReusltDTO login(UserReqDTO user) {
         // 首先将账号密码封装为UsernamePasswordAuthentication形式
        UsernamePasswordAuthenticationToken AuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        // 调用authenticationManager中属于UsernamePasswordAuthenticationToken对象的authenticate方法，如果认证成功，则返回被封装的用户消息authenticate对象，反之为null
        Authentication authenticate = authenticationManager.authenticate(AuthenticationToken);
        if(authenticate == null){
            throw new RuntimeException("登陆失败");
        }
        // authenticate对象中并没有直接存储用户消息，而是在其principal字段中，所以
        LoginUser principal = (LoginUser) authenticate.getPrincipal();
        UserInfo loginUser = principal.getUser();
        String token = JwtUtils.getJwtToken(loginUser.getId().toString());
        HashMap<String, String> map = new HashMap<>(8);
        map.put("token",token);
        cache.setCacheObject("login:"+loginUser.getId(),principal);
        return new ResponseReusltDTO(200,"登陆成功",map);
    }
}
