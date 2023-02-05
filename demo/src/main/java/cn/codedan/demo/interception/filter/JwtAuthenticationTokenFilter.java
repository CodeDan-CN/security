package cn.codedan.demo.interception.filter;

import cn.codedan.demo.model.entity.LoginUser;
import cn.codedan.demo.model.entity.UserInfo;
import cn.codedan.demo.util.JwtUtils;
import cn.codedan.demo.util.RedisCache;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @ClassName: JwtAuthenticationTokenFilter
 * @Description: TODO
 * @Author: codedan
 * @Date: 2022/12/13 18:42
 * @Version: 1.0.0
 **/
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("token");
        if( token == null ){
            filterChain.doFilter(request,response);
            return;
        }
        String userid = JwtUtils.getMemberIdByJwtToken(token);
        String redisKey = "login:"+userid;
        LoginUser user = new JSONObject(redisCache.getCahcheObject(redisKey)).toBean(LoginUser.class);
        if(Objects.isNull(user) ){
            throw new RuntimeException("用户未登陆");
        }
        // 新增权限数据放入
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request,response);
    }
}
