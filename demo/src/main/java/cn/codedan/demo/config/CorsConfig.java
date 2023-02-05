package cn.codedan.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: CorsConfig
 * @Description: TODO
 * @Author: CodeDan
 * @Date: 2023/2/5 16:16
 * @Version: 1.0.0
 **/
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路径
        registry.addMapping("/**")
                // 设置允许跨域域名
              .allowedOrigins("*")
                // 设置允许的请求方式
              .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // 是否允许cookie
              .allowCredentials(true)
                // 设置允许的header属性
              .allowedHeaders("")
                // 设置允许跨域的时间
              .maxAge(3600);
    }
}
