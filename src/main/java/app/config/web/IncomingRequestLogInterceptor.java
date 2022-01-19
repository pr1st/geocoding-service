package app.config.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class IncomingRequestLogInterceptor implements HandlerInterceptor {
    private static final Log log = LogFactory.getLog(IncomingRequestLogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Incoming request: " + request.getMethod() + " " + request.getRequestURL());
        }
         return true;
    }
}
