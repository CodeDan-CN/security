package cn.codedan.common;

import cn.codedan.model.entity.OauthClientDetails;
import cn.codedan.service.OauthClientDetailsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.stereotype.Component;


/**
 * @author CodeDan
 */
public class JdbcTokenStoreUserApprovalHandler extends TokenStoreUserApprovalHandler {


    @Autowired
    OauthClientDetailsService oAuthService;

    @Override
    public boolean isApproved(AuthorizationRequest authorizationRequest, Authentication userAuthentication) {
        if (super.isApproved(authorizationRequest, userAuthentication)) {
            return true;
        }
        if (!userAuthentication.isAuthenticated()) {
            return false;
        }
        String clientId = authorizationRequest.getClientId();
        OauthClientDetails clientDetails = oAuthService.getOne(new QueryWrapper<OauthClientDetails>().lambda().eq(OauthClientDetails::getClientId, clientId));
        return clientDetails != null;
    }
}