package cn.codedan.demo.handler;

import cn.codedan.demo.model.dto.ResponseReusltDTO;
import cn.hutool.json.JSONUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: AuthenticationEntryPointImpl
 * @Description: TODO
 * @Author: CodeDan
 * @Date: 2023/2/5 15:40
 * @Version: 1.0.0
 **/
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        // 进行异常处理
        ResponseReusltDTO fail = ResponseReusltDTO.fail(e.getMessage());
//        WebUtils.renderString(httpServletResponse, JSONUtil.toJsonStr(fail));
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.getWriter().write(JSONUtil.toJsonStr(fail));
    }
}
