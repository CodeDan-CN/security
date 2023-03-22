package cn.codedan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.codedan.model.entity.OauthAccessToken;
import cn.codedan.service.OauthAccessTokenService;
import cn.codedan.mapper.OauthAccessTokenMapper;
import org.springframework.stereotype.Service;

/**
* @author codedan
* @description 针对表【oauth_access_token】的数据库操作Service实现
* @createDate 2023-02-28 11:44:07
*/
@Service
public class OauthAccessTokenServiceImpl extends ServiceImpl<OauthAccessTokenMapper, OauthAccessToken>
    implements OauthAccessTokenService{

}




