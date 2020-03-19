package com.everwing.cloud.auth.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.everwing.cloud.auth.request.RequestParameterWrapper;
import com.everwing.cloud.auth.request.ResponseWrapper;
import com.everwing.cloud.auth.utils.IpAddressUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author DELL shiny
 * @date 2020/3/19
 */
@Slf4j
@Component
public class IntegrationAuthenticationFilter extends GenericFilterBean implements ApplicationContextAware {
    //登录类型参数名
    private static final String AUTH_TYPE_PARM_NAME = "auth_type";

    //需要拦截的路由
    private static final String OAUTH_TOKEN_URL = "/oauth/token";

    private RequestMatcher requestMatcher;
    private ApplicationContext applicationContext;

    public IntegrationAuthenticationFilter() {
        this.requestMatcher = new OrRequestMatcher(
                new AntPathRequestMatcher(OAUTH_TOKEN_URL, "GET"),
                new AntPathRequestMatcher(OAUTH_TOKEN_URL, "POST")
        );
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (requestMatcher.matches(request)) {
            RequestParameterWrapper requestParameterWrapper = new RequestParameterWrapper(request);
            ResponseWrapper wrapperResponse = new ResponseWrapper((HttpServletResponse) servletResponse);
            Map<String, String[]> params = requestParameterWrapper.getParameterMap();
            //Cloud OAuth2 密码模式需要判断Request是否存在password参数，
            //如果不存在会抛异常结束认证
            //所以在调用doFilter方法前添加password参数
            if (requestParameterWrapper.getParameter("password") == null) {
                requestParameterWrapper.addParameter("password", "");
            }
            filterChain.doFilter(requestParameterWrapper, wrapperResponse);
            //获取返回值
            byte[] content = wrapperResponse.getContent();
            //判断是否有值
            if (content.length > 0) {
                String str = new String(content, "UTF-8");
                JSONObject jsonObject = JSON.parseObject(str);
                if (jsonObject.containsKey("error")) {
                    log.error("用户:[{}]登录获取token失败！，ip:[{}],参数:[{}]", params.get("username"), IpAddressUtil.getIpAddress(request), JSON.toJSONString(params));
                }
                if (jsonObject.containsKey("access_token")) {
                    log.info("用户:[{}]登录获取token成功！，ip:[{}],参数:[{}]", params.get("username"), IpAddressUtil.getIpAddress(request), JSON.toJSONString(params));
                }
                String ciphertext = null;
                try {
                    //......根据需要处理返回值
                    ciphertext = str;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //把返回值输出到客户端
                ServletOutputStream out = servletResponse.getOutputStream();
                out.write(ciphertext.getBytes());
                out.flush();
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}