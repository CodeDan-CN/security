package cn.codedan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.codedan.model.entity.OauthApprovals;
import cn.codedan.service.OauthApprovalsService;
import cn.codedan.mapper.OauthApprovalsMapper;
import org.springframework.stereotype.Service;

/**
* @author codedan
* @description 针对表【oauth_approvals】的数据库操作Service实现
* @createDate 2023-02-28 11:44:07
*/
@Service
public class OauthApprovalsServiceImpl extends ServiceImpl<OauthApprovalsMapper, OauthApprovals>
    implements OauthApprovalsService{

}




