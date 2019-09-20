package work.icql.springcloud.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import work.icql.springcloud.common.entity.Result;
import work.icql.springcloud.common.entity.StatusCode;
import work.icql.springcloud.common.util.FastjsonUtils;
import work.icql.springcloud.common.util.JwtUtils;
import work.icql.springcloud.gateway.config.FilterProperties;
import work.icql.springcloud.gateway.config.JwtProperties;

import javax.servlet.http.HttpServletRequest;

/**
 * @author icql
 * @version 1.0
 * @date 2018/10/24 19:14
 * @Title JwtFilter
 * @Description JwtFilter
 */
@Component
public class JwtFilter extends ZuulFilter {
    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private FilterProperties filterProperties;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 5;
    }

    @Override
    public boolean shouldFilter() {
        // 获取上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        // 获取request
        HttpServletRequest req = ctx.getRequest();
        // 获取路径
        String requestURI = req.getRequestURI();
        // 判断白名单
        return !isAllowPath(requestURI);
    }

    @Override
    public Object run() throws ZuulException {
        // 获取上下文
        RequestContext context = RequestContext.getCurrentContext();
        // 获取request
        HttpServletRequest request = context.getRequest();
        String token = request.getHeader(jwtProperties.getRequestHeaderKey());
        try {
            JwtUtils.getInfoByToken(jwtProperties.getPublicKey(), token);
        } catch (ExpiredJwtException expiredJwtException) {
            //授权过期，需要刷新授权的重新请求auth，利用旧token获取新的token
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(StatusCode.EXPIREDERROR);
            context.setResponseBody(FastjsonUtils.object2json(Result.build(false, StatusCode.EXPIREDERROR)));
        } catch (Exception e) {
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(StatusCode.ACCESSERROR);
            context.setResponseBody(FastjsonUtils.object2json(Result.build(false, StatusCode.ACCESSERROR)));
        }
        return null;
    }

    private boolean isAllowPath(String requestURI) {
        // 定义一个标记
        boolean flag = false;
        // 遍历允许访问的路径
        for (String path : this.filterProperties.getAllowPaths()) {
            // 然后判断是否是符合
            if(requestURI.startsWith(path)){
                flag = true;
                break;
            }
        }
        return flag;
    }
}
