package cn.codedan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import javax.annotation.Resource;

/**
 * @ClassName: AuthorizationServerAdapterConfig
 * @Description: SpringSecurityAuthorizationServerAdapterConfig
 * @Author: CodeDan
 * @Date: 2023/2/26 16:06
 * @Version: 1.0.0
 **/
@EnableAuthorizationServer
@Configuration
public class AuthorizationServerAdapterConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * token持久化的方式，这里采用的是内存
     */
    @Bean
    TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    /**
     * 授权码业务处理方式，这里采用内存存储以及处理
     */
    @Bean
    AuthorizationCodeServices authorizationCodeServices() {
        return new InMemoryAuthorizationCodeServices();
    }

    /**
     * 配置token业务操作对象,主要用来配置 Token 的一些基本信息，例如 Token 是否支持刷新、Token 的存储位置、Token 的有效期以及刷新 Token 的有效期
     */
    @Bean
    AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices services = new DefaultTokenServices();
        services.setClientDetailsService(clientDetailsService);
        services.setSupportRefreshToken(true);
        services.setTokenStore(tokenStore);
        services.setAccessTokenValiditySeconds(60 * 60 * 2);
        services.setRefreshTokenValiditySeconds(60 * 60 * 24 * 3);
        return services;
    }
    @Resource
    private PasswordEncoder bCryptPasswordEncoder;
    @Autowired
    TokenStore tokenStore;
    /**
     * 客户端信息业务处理的对象
     */
    @Autowired
    ClientDetailsService clientDetailsService;

    /**
     * 在内存中校验客户端信息
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                // 设置客户端唯一标识
                .withClient("myClient")
                // 设置客户端Secret 类似于客户端的密码
                .secret(bCryptPasswordEncoder.encode("123"))
                .resourceIds("res1")
                // 设置当前Oauth2的授权方式为授权码模式
                .authorizedGrantTypes("authorization_code","refresh_token")
                // 发放token的权限范围
                .scopes("all")
                // 重定向资源服务器地址并携带上code
                .redirectUris("http://localhost:8082/index.html");
    }


    /**
     * 当资源服务器接受到携带token的请求后，为了校验token是否正确，就会来请求授权服务器
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("permitAll()")         //开放token检验
                .allowFormAuthenticationForClients();    //表单认证（申请令牌）
    }

    /**
     * 这里用来配置令牌的访问端点和令牌服务。authorizationCodeServices用来配置授权码的存储，这里我们是存在在内存中，tokenServices 用来配置令牌的存储，即 access_token 的存储位置
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authorizationCodeServices(authorizationCodeServices()) //code操作业务处理对象插入
                .tokenServices(tokenServices()); //token操作业务处理对象插入
    }
}
