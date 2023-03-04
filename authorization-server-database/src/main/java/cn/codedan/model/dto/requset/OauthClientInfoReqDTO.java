package cn.codedan.model.dto.requset;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: OauthClientInfoReqDTO
 * @Description: 客户端传入DTO
 * @Author: CodeDan
 * @Date: 2023/3/4 10:44
 * @Version: 1.0.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OauthClientInfoReqDTO {

    private String clientId;

    /**
     *
     */
    private String resourceIds;

    /**
     *
     */
    private String clientSecret;

    /**
     *
     */
    private String scope;

    /**
     *
     */
    private String authorizedGrantTypes;

    /**
     *
     */
    private String webServerRedirectUri;

    /**
     *
     */
    private String authorities;

    /**
     *
     */
    private Integer accessTokenValidity;

    /**
     *
     */
    private Integer refreshTokenValidity;

    /**
     *
     */
    private String additionalInformation;

    /**
     *
     */
    private String autoapprove;


}
