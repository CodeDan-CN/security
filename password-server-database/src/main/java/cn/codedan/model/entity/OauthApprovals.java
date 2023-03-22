package cn.codedan.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName oauth_approvals
 */
@TableName(value ="oauth_approvals")
@Data
public class OauthApprovals implements Serializable {
    /**
     * 
     */
    @TableField(value = "userId")
    private String userid;

    /**
     * 
     */
    @TableField(value = "clientId")
    private String clientid;

    /**
     * 
     */
    @TableField(value = "scope")
    private String scope;

    /**
     * 
     */
    @TableField(value = "STATUS")
    private String status;

    /**
     * 
     */
    @TableField(value = "expiresAt")
    private Date expiresat;

    /**
     * 
     */
    @TableField(value = "lastModifiedAt")
    private Date lastmodifiedat;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}