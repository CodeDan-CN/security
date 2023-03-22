package cn.codedan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.codedan.model.entity.PermissionInfo;
import cn.codedan.service.PermissionInfoService;
import cn.codedan.mapper.PermissionInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author codedan
* @description 针对表【permission_info】的数据库操作Service实现
* @createDate 2023-02-28 11:44:08
*/
@Service
public class PermissionInfoServiceImpl extends ServiceImpl<PermissionInfoMapper, PermissionInfo>
    implements PermissionInfoService{

}




