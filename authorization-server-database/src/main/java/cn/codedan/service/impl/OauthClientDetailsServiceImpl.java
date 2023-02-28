package cn.codedan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.codedan.model.entity.OauthClientDetails;
import cn.codedan.service.OauthClientDetailsService;
import cn.codedan.mapper.OauthClientDetailsMapper;
import org.springframework.stereotype.Service;

/**
* @author codedan
* @description 针对表【oauth_client_details】的数据库操作Service实现
* @createDate 2023-02-28 11:44:07
*/
@Service
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetails>
    implements OauthClientDetailsService{

}




