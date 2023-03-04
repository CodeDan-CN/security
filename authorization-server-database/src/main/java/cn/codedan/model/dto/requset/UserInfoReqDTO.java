package cn.codedan.model.dto.requset;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: UserInfoReqDTO
 * @Description: 用户注册请求DTO
 * @Author: CodeDan
 * @Date: 2023/3/4 11:09
 * @Version: 1.0.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoReqDTO {

    private String username;

    private String password;

}
