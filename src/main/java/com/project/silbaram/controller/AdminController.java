package com.project.silbaram.controller;

import com.project.silbaram.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final MemberServiceImpl memberService;

//    @GetMapping("")
//    public String adminIndex() {
//        return "silbaram/admin/admin_main";
//    }

    @GetMapping("/main")
    public String adminPage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long mid = (Long) session.getAttribute("mid");

        // 로그인하지 않은 경우 로그인 페이지로 리다이렉트 또는 에러 처리 등을 수행
        if (mid == null) {
            return "redirect:/login"; // 또는 다른 처리를 수행하도록 수정
        }

        // 현재 사용자가 관리자인지 확인
        if (memberService.isAdminUser(mid)) {
            return "admin/admin_main"; // 관리자 페이지로 이동 (뷰 또는 데이터 반환)
        }

        // 관리자가 아닌 경우 다른 처리를 수행하도록 수정 (예: 에러 처리, 리다이렉트)
        return "index"; // 또는 다른 처리를 수행하도록 수정
    }
}
