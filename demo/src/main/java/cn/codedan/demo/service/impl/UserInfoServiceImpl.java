package cn.codedan.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.codedan.demo.model.entity.UserInfo;
import cn.codedan.demo.service.UserInfoService;
import cn.codedan.demo.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author CodeDan
* @description 针对表【user_detail】的数据库操作Service实现
* @createDate 2023-02-04 22:27:29
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{

}




