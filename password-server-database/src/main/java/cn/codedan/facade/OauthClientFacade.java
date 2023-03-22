package cn.codedan.facade;

import cn.codedan.model.dto.requset.OauthClientInfoReqDTO;
import cn.codedan.model.entity.OauthClientDetails;
import cn.codedan.service.OauthClientDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @ClassName: OauthClientFacade
 * @Description: 授权服务器客户端业务处理类
 * @Author: CodeDan
 * @Date: 2023/3/4 10:52
 * @Version: 1.0.0
 **/
@Service
@Slf4j
public class OauthClientFacade {

    @Autowired
    private OauthClientDetailsService oauthClientDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 新增一个客户端数据
     * @param oauthClientInfoReqDTO
     */
    public void add(OauthClientInfoReqDTO oauthClientInfoReqDTO){
        OauthClientDetails oauthClientDetails = new OauthClientDetails();
        BeanUtils.copyProperties(oauthClientInfoReqDTO,oauthClientDetails);
        log.info("存储客户端消息为:{}",oauthClientDetails);
        oauthClientDetails.setClientSecret(passwordEncoder.encode(oauthClientDetails.getClientSecret()));
        oauthClientDetailsService.save(oauthClientDetails);
    }
}
