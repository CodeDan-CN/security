package cn.codedan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.codedan.model.entity.OauthRefreshToken;
import cn.codedan.service.OauthRefreshTokenService;
import cn.codedan.mapper.OauthRefreshTokenMapper;
import org.springframework.stereotype.Service;

/**
* @author codedan
* @description 针对表【oauth_refresh_token】的数据库操作Service实现
* @createDate 2023-02-28 11:44:08
*/
@Service
public class OauthRefreshTokenServiceImpl extends ServiceImpl<OauthRefreshTokenMapper, OauthRefreshToken>
    implements OauthRefreshTokenService{

}




