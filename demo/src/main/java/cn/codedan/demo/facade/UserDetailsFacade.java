package cn.codedan.demo.facade;

import cn.codedan.demo.model.dto.UserReqDTO;
import cn.codedan.demo.model.entity.LoginUser;
import cn.codedan.demo.model.entity.UserInfo;
import cn.codedan.demo.service.UserInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @ClassName: UserDetailsServiceImpl
 * @Description: TODO
 * @Author: codedan
 * @Date: 2022/12/13 17:02
 * @Version: 1.0.0
 **/
@Service
public class UserDetailsFacade implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public UserDetails loadUserByUsername(String userAccount) throws UsernameNotFoundException {
        // 查询用户信息
        UserInfo userInfo = userInfoService.getOne(new QueryWrapper<UserInfo>().lambda().eq(UserInfo::getUserName, userAccount));
        if( StringUtils.isEmpty(userInfo) ){
            throw new RuntimeException("用户不存在");
        }
        ArrayList<String> authors = new ArrayList<>(Arrays.asList("test", "admin"));
        LoginUser loginUser = new LoginUser();
        loginUser.setUser(userInfo);
        loginUser.setAuthorityNames(authors);
        return loginUser;
    }

    /**
     * 注册账号
     * @param userReqDTO 用户信息传入参数
     * @return
     */
    public boolean sign(UserReqDTO userReqDTO){
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userReqDTO,userInfo);
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        return userInfoService.save(userInfo);
    }


}
