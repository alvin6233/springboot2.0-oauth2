package cn.merryyou.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author: dongxiaozhuang/alvin
 * @datetime: 2019-02-21 20:40
 * @description: new world begin.
 */
@Component
@Slf4j
public class AppLoginInFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        log.info("登录失败");
        //设置状态码
        response.setStatus(500);
        response.setContentType("application/json;charset=UTF-8");
        //将 登录失败 信息返回
        response.getWriter().write(objectMapper.writeValueAsString(exception.getMessage()));
    }
}
