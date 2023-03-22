package cn.codedan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName: SecurityPasswordDataBaseApplication
 * @Description: 密码模式的数据库版本
 * @Author: CodeDan
 * @Date: 2023/3/22 22:04
 * @Version: 1.0.0
 **/
@SpringBootApplication
@MapperScan(basePackages = "cn.codedan.mapper")
public class SecurityPasswordDataBaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityPasswordDataBaseApplication.class,args);
    }
}
