package cn.codedan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName: SecurityAuthorizationDataBaseApplication
 * @Description: 授权服务器（数据库版）启动类
 * @Author: CodeDan
 * @Date: 2023/2/28 10:25
 * @Version: 1.0.0
 **/
@SpringBootApplication
public class SecurityAuthorizationDataBaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityAuthorizationDataBaseApplication.class,args);
    }
}
