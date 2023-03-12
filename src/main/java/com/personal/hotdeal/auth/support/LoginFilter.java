package com.personal.hotdeal.auth.support;

import com.personal.hotdeal.common.exception.CustomException;
import com.personal.hotdeal.common.exception.ErrorCode;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {
    private static final String[] whitelist = {"/", "/api/member", "/api/auth/login", "/logout", "/css/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            log.info("인층 체크 필터 시작{}", requestURI);
            if (isLoginCheckPath(requestURI)) {
                log.info("인증 체크 로직 실행 {}", requestURI);
                HttpSession session = httpRequest.getSession(false);
                Cookie[] cookies = ((HttpServletRequest) request).getCookies();
                Cookie findCookie=null;
                for (Cookie cookie : cookies) {
                    log.info(cookie.getName());
                    if ("sessionId".equals(cookie.getName())) {
                        findCookie=cookie;
                    }
                }
                if (findCookie==null || session == null || session.getAttribute(findCookie.getValue()) == null) {
                    log.info("미인증 사용자 요청 {}", requestURI);
                    throw new CustomException(ErrorCode.USER_UNAUTORIZED);
                }
            }
            chain.doFilter(request, response);

        } catch (Exception e) {
            throw e;
        } finally {
            log.info("인증 체크 필터 종료 {}", requestURI);
        }
    }

    /**
     * whiteList의 경우 인증 체크를 안하도록 한다.
     */
    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
}
