package cn.codedan.config;

import cn.codedan.common.JdbcTokenStoreUserApprovalHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import javax.sql.DataSource;

/**
 * @ClassName: OAuth2Config
 * @Description: TODO
 * @Author: CodeDan
 * @Date: 2023/3/1 22:46
 * @Version: 1.0.0
 **/
@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    @Bean
    public ClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    public TokenStore jdbcTokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices services = new DefaultTokenServices();
        services.setClientDetailsService(clientDetailsService());
        services.setSupportRefreshToken(true);
        services.setTokenStore(jdbcTokenStore());
        return services;
    }

    /**
     * 替换TokenEndpoint对象中/oauth/token接口中获取TokenRequest的实例为DefaultOAuth2RequestFactory
     * @return
     */
    @Bean
    public OAuth2RequestFactory oAuth2RequestFactory() {
        return new DefaultOAuth2RequestFactory(clientDetailsService());
    }
//    @Bean
//    public AuthorizationCodeServices authorizationCodeServices() {
//        return new JdbcAuthorizationCodeServices(dataSource);
//    }
    @Bean
    public UserApprovalHandler userApprovalHandler() {
        JdbcTokenStoreUserApprovalHandler approvalHandler = new JdbcTokenStoreUserApprovalHandler();
        approvalHandler.setTokenStore(jdbcTokenStore());
        approvalHandler.setClientDetailsService(clientDetailsService());
        approvalHandler.setRequestFactory(oAuth2RequestFactory());
        return approvalHandler;
    }
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    TokenStore tokenStore;

    @Autowired
    ClientDetailsService clientDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 配置数据从oauth_client_details表读取来存储
        clients.withClientDetails(clientDetailsService());
    }

    /**
     * 自定义Oauth2协议确认授权方式以及认证请求链接等配置
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(jdbcTokenStore()).authenticationManager(authenticationManager)
                //自定义AccessToken
                //.accessTokenConverter(accessTokenConverter)
                //设置userDetailsService
                //.userDetailsService(userDetailFacade)
                //授权码储存
//                .authorizationCodeServices(authorizationCodeServices())
                //设置userApprovalHandler
                .userApprovalHandler(userApprovalHandler())
                //设置tokenServices
                .tokenServices(tokenServices())
                //支持获取token方式
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST,HttpMethod.PUT,HttpMethod.DELETE,HttpMethod.OPTIONS)
                //刷新token
                .reuseRefreshTokens(true);
    }

    /**
     * 认证服务器的安全配置
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                // 开启/oauth/token_key验证端口认证权限访问
                .tokenKeyAccess("isAuthenticated()")
                //  开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("isAuthenticated()")
                //允许表单认证 在授权码模式下会导致无法根据code获取token　
                .allowFormAuthenticationForClients();
    }
}
