package cn.codedan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.codedan.model.entity.OauthClientToken;
import cn.codedan.service.OauthClientTokenService;
import cn.codedan.mapper.OauthClientTokenMapper;
import org.springframework.stereotype.Service;

/**
* @author codedan
* @description 针对表【oauth_client_token】的数据库操作Service实现
* @createDate 2023-02-28 11:44:08
*/
@Service
public class OauthClientTokenServiceImpl extends ServiceImpl<OauthClientTokenMapper, OauthClientToken>
    implements OauthClientTokenService{

}




