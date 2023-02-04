package cn.codedan.demo.service;


import cn.codedan.demo.model.dto.ResponseReusltDTO;
import cn.codedan.demo.model.dto.UserReqDTO;

/**
 * @ClassName: LoginService
 * @Description: TODO
 * @Author: codedan
 * @Date: 2022/12/13 17:30
 * @Version: 1.0.0
 **/
public interface LoginService {

    ResponseReusltDTO login(UserReqDTO user);

}
