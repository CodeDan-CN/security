package cn.codedan.demo.mapper;

import cn.codedan.demo.model.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resources;

/**
* @author CodeDan
* @description 针对表【user_detail】的数据库操作Mapper
* @createDate 2023-02-04 22:27:29
* @Entity cn.codedan.demo.model.entity.UserInfo
*/
@Mapper
@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}




