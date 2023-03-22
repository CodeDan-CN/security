package cn.codedan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.codedan.model.entity.OauthCode;
import cn.codedan.service.OauthCodeService;
import cn.codedan.mapper.OauthCodeMapper;
import org.springframework.stereotype.Service;

/**
* @author codedan
* @description 针对表【oauth_code】的数据库操作Service实现
* @createDate 2023-02-28 11:44:08
*/
@Service
public class OauthCodeServiceImpl extends ServiceImpl<OauthCodeMapper, OauthCode>
    implements OauthCodeService{

}




