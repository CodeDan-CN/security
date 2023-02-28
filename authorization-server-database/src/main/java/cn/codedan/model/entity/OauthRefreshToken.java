package cn.codedan.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName oauth_refresh_token
 */
@TableName(value ="oauth_refresh_token")
@Data
public class OauthRefreshToken implements Serializable {
    /**
     * 
     */
    @TableField(value = "token_id")
    private String tokenId;

    /**
     * 
     */
    @TableField(value = "token")
    private byte[] token;

    /**
     * 
     */
    @TableField(value = "authentication")
    private byte[] authentication;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}