package cn.codedan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @ClassName: SecurityConfig
 * @Description: Spring Security 配置类
 * @Author: CodeDan
 * @Date: 2023/2/26 15:57
 * @Version: 1.0.0
 **/
@Configuration
public class SpringSecurityAdapterConfig extends WebSecurityConfigurerAdapter {

    /**
     * 将Security特有的密码加密类实例交给Spring容器管理
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Security常规配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf
        http.csrf().disable().formLogin();
    }

    /**
     * 在内存中配置用户信息（登录时使用）
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("CodeDan")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .roles("root")
                .and()
                .withUser("ZHANGLDC")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .roles("admin");
    }
}
