package cn.codedan.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName oauth_code
 */
@TableName(value ="oauth_code")
@Data
public class OauthCode implements Serializable {
    /**
     * 
     */
    @TableField(value = "CODE")
    private String code;

    /**
     * 
     */
    @TableField(value = "authentication")
    private byte[] authentication;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}