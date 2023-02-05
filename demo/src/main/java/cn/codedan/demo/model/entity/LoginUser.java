package cn.codedan.demo.model.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName: LoginUser
 * @Description: TODO
 * @Author: codedan
 * @Date: 2022/12/13 17:05
 * @Version: 1.0.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements UserDetails {

    /**
     * 自定义的用户类，里面必须包含三个字段(多的不作限制)，用户名，密码，角色列表。
     */
    private UserInfo user;

    /**
     * 权限存储集合
     */
    private List<String> authorityNames;

    /**
     * 权限存储集合
     */
    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;

    /**
     * 这里将数据库的查出来的角色列表进行GrantedAuthority转化
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(StringUtils.isEmpty(authorities)){
            ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for( String authorityName : authorityNames){
                grantedAuthorities.add(new SimpleGrantedAuthority(authorityName));
            }
            this.authorities = grantedAuthorities;
        }
        return authorities;
    }

    /**
     * 获取当前用户的密码
     * @return
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * 获取当前用户的用户名
     * @return
     */
    @Override
    public String getUsername() {
        return user.getUserName();
    }

    /**
     * 是否过期，改为true
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 是否超时
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
