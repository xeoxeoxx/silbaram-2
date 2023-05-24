package com.project.silbaram.intercepter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.silbaram.dto.MemberDTO;
import com.project.silbaram.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AutoLoginInterceptor implements HandlerInterceptor {

    private final MemberService memberService;

    public AutoLoginInterceptor(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 쿠키에서 remember-me 값 가져오기
        String rememberMeCookieValue = getCookieValue(request, "remember-me");

        if (rememberMeCookieValue != null) {
            // remember-me 쿠키가 존재하는 경우

            // UUID 값으로 회원 조회
            MemberDTO memberDTO = memberService.getByUuid(rememberMeCookieValue);

            if (memberDTO != null) {
                // 회원이 존재하는 경우 자동 로그인 처리
                HttpSession session = request.getSession();
                session.setAttribute("mid", memberDTO.getMid());
                // 추가적인 처리...

                return true; // 요청 계속 진행
            }
        }

        return true; // 요청 계속 진행
    }

    private String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }
}