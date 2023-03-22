package cn.codedan.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName oauth_client_token
 */
@TableName(value ="oauth_client_token")
@Data
public class OauthClientToken implements Serializable {
    /**
     * 
     */
    @TableId(value = "authentication_id")
    private String authenticationId;

    /**
     * 
     */
    @TableField(value = "token_id")
    private String tokenId;

    /**
     * 
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 
     */
    @TableField(value = "client_id")
    private String clientId;

    /**
     * 
     */
    @TableField(value = "token")
    private byte[] token;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}