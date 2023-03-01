package cn.codedan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/**
 * @ClassName: ResourceAdapterConfig
 * @Description: 资源服务器的Security适配器配置
 * @Author: CodeDan
 * @Date: 2023/2/26 16:49
 * @Version: 1.0.0
 **/
@Configuration
@EnableResourceServer
public class ResourceAdapterConfig extends ResourceServerConfigurerAdapter {

    /**
     * 接受到携带token的请求时，资源服务器向授权服务器发起校验请求
     * @return
     */
    @Bean
    RemoteTokenServices tokenServices() {
        RemoteTokenServices services = new RemoteTokenServices();
        services.setCheckTokenEndpointUrl("http://localhost:8081/oauth/check_token");
        services.setClientId("myClient");
        services.setClientSecret("123");
        return services;
    }

    /**
     * 客户端权限业务处理
     * @param resources
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("res1").tokenServices(tokenServices());
    }

    /**
     * 常规配置
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated();
    }
}
