package cn.codedan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.codedan.model.entity.UserInfo;
import cn.codedan.service.UserInfoService;
import cn.codedan.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author codedan
* @description 针对表【user_info】的数据库操作Service实现
* @createDate 2023-02-28 11:44:08
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{

}




