package cn.codedan.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @ClassName: ResourceController
 * @Description: TODO
 * @Author: CodeDan
 * @Date: 2023/2/27 21:06
 * @Version: 1.0.0
 **/
@RestController
@RequestMapping("/client")
public class OtherServerController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 第三方客户端免登重要操作请求，即获取授权服务器登录完成重定向过来的code，并使用这个code去授权服务器获取access_token，最后携带access_token去调用资源服务器的资源
     * @param code
     * @return
     */
    @GetMapping("/hello")
    public String hello(String code){
        if(StringUtils.isEmpty(code)){
           return "请去授权：http://localhost:8081/oauth/authorize?client_id=myClient&response_type=code&scope=all&redirect_uri=http://localhost:8082/client/hello";
        }
        //配置一下通过code获取access_token的请求
        LinkedMultiValueMap<Object, Object> requestQuery = new LinkedMultiValueMap<>();
        // 这里的code没有好说的
        requestQuery.add("code",code);
        // 当前客户端在授权服务器中注册的client_id
        requestQuery.add("client_id","oa");
        // 当前客户端在授权服务器中注册的client_secret
        requestQuery.add("client_secret","123456");
        // 这个数据存在的意义在于校验code，这里的跳转地址要和应用在授权服务器注册时使用的地址一致
        requestQuery.add("redirect_uri", "http://localhost:8082/client/hello");
        // 授权类型
        requestQuery.add("grant_type","authorization_code");
        // 发起请求
        Map<String,String> response = restTemplate.postForObject("http://localhost:8081/oauth/token", requestQuery, Map.class);
        // 提取token
        String access_token = response.get("access_token");
        System.out.println("access_token:"+access_token);
        // 装配一下access_token访问资源服务器的请求
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer "+access_token);
        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:8080/info/get", HttpMethod.GET, httpEntity, String.class);
        return exchange.getBody();
    }


    @GetMapping("/password")
    public String password(String username,String password){
        MultiValueMap<String, String> requestQuery = new LinkedMultiValueMap<>();
        requestQuery.add("username", username);
        requestQuery.add("password", password);
        requestQuery.add("client_secret", "123456");
        requestQuery.add("client_id", "myapp");
        requestQuery.add("grant_type", "password");
        // 发起请求
        Map<String,String> response = restTemplate.postForObject("http://localhost:8081/oauth/token", requestQuery, Map.class);
        // 提取token
        String access_token = response.get("access_token");
        System.out.println("access_token:"+access_token);
        // 装配一下access_token访问资源服务器的请求
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer "+access_token);
        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:8080/info/get", HttpMethod.GET, httpEntity, String.class);
        return exchange.getBody();
    }

    @GetMapping("/clientModel")
    public String clientModel(){
        MultiValueMap<String, String> requestQuery = new LinkedMultiValueMap<>();
        requestQuery.add("client_secret", "123456");
        requestQuery.add("client_id", "myclient");
        requestQuery.add("grant_type", "client_credentials");
        // 发起请求
        Map<String,String> response = restTemplate.postForObject("http://localhost:8081/oauth/token", requestQuery, Map.class);
        // 提取token
        String access_token = response.get("access_token");
        System.out.println("access_token:"+access_token);
        // 装配一下access_token访问资源服务器的请求
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer "+access_token);
        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:8080/info/get", HttpMethod.GET, httpEntity, String.class);
        return exchange.getBody();
    }
}
