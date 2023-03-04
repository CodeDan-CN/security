package cn.codedan.facade;

import cn.codedan.model.dto.requset.UserInfoReqDTO;
import cn.codedan.model.entity.UserInfo;
import cn.codedan.service.UserInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * @ClassName: UserDetailFacade
 * @Description: SpringSecurity处理用户业务逻辑类
 * @Author: CodeDan
 * @Date: 2023/2/28 11:09
 * @Version: 1.0.0
 **/
@Service
public class UserDetailFacade implements UserDetailsService {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo one = userInfoService.getOne(new QueryWrapper<UserInfo>().lambda().eq(UserInfo::getUsername, s));
        if(StringUtils.isEmpty(one)){
            throw new UsernameNotFoundException("登录用户"+s+"没有注册啊！！！！！");
        }
        // 不想创建新对象继承UserDetails，这里用Security的User(org.springframework.security.core.userdetails.User)对象构造去造一个
        // 继承UserDetails的写法在JWT模式中有哦
        // 根据用户信息获取角色，再通过角色去获取对象集合,这里就不去搞太麻烦了,不过我源代码里相关数据库表的对象已经有了，可以自己补充
        return new User(one.getUsername(),one.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROOT")));
    }

    /**
     * 添加一个用户
     * @param userInfoReqDTO
     */
    public void add(UserInfoReqDTO userInfoReqDTO) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoReqDTO,userInfo);
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoService.save(userInfo);
    }
}
