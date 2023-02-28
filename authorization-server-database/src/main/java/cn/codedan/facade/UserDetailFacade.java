package cn.codedan.facade;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @ClassName: UserDetailFacade
 * @Description: SpringSecurity处理用户业务逻辑类
 * @Author: CodeDan
 * @Date: 2023/2/28 11:09
 * @Version: 1.0.0
 **/
public class UserDetailFacade implements UserDetailsService {



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {


        return null;
    }
}
