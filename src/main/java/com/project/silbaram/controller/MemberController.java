package com.project.silbaram.controller;

import com.project.silbaram.dto.MemberDTO;
import com.project.silbaram.dto.PageRequestDTO;
import com.project.silbaram.dto.PageResponseDTO;
import com.project.silbaram.service.CartServiceImpl;
import com.project.silbaram.service.MemberServiceImpl;
import com.project.silbaram.service.MyBookListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MemberController {
    private final MemberServiceImpl memberService;

    private final MyBookListService myBookListService;
    private final CartServiceImpl cartService;

    @GetMapping({"/", "", "/index"})
    public String index() {
        return "index";
    }


    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("memberDTO", new MemberDTO());
        return "member/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userId, @RequestParam String password,
                        @RequestParam(value = "auto", required = false) String auto, MemberDTO memberDTO, HttpServletResponse response,
                        HttpSession session, Model model) {
        Long mid = memberService.login(userId, password);

        if(mid == null) {
            log.info("login fails!");
            model.addAttribute("msg","아이디와 비밀번호를 확인해주세요");
            return "redirect:/login";
        }

        else  {
            model.addAttribute("auto", auto); // 자동로그인 체크여부
            boolean rememberMe = auto != null && auto.equals("on"); // auto 를 체크하면 true
            if(rememberMe) {
                String uuid = UUID.randomUUID().toString(); // 임의의 문자열 생성
                memberService.updateUuid(mid,uuid);
                memberDTO.setUuid(uuid);

                Cookie rememberCookie = new Cookie("remember-me", uuid);
                rememberCookie.setMaxAge(60 * 60 * 24 * 7); // 쿠키의 유효기간은 1주일
                rememberCookie.setPath("/");

                response.addCookie(rememberCookie);

            } else {
                // 자동 로그인 체크가 해제된 경우 기존의 자동 로그인 쿠키 제거
                Cookie rememberCookie = new Cookie("remember-me", "");
                rememberCookie.setMaxAge(0);
                rememberCookie.setPath("/");
                response.addCookie(rememberCookie);
            }

            session.setAttribute("mid", mid);
            log.info("login success!");
            return "redirect:/index";
        }

    }


    @PostMapping("/logout")
    public String logoutPOST(HttpServletRequest request, HttpServletResponse response) {
        //세션을 삭제
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.removeAttribute("mid");
        }
        // 자동 로그인 쿠키 제거
        Cookie rememberCookie = new Cookie("remember-me", "");
        rememberCookie.setMaxAge(0);
        rememberCookie.setPath("/");
        response.addCookie(rememberCookie);

        return "redirect:/index";
    }

    @GetMapping("/mypage")
    public String mypage(HttpSession session, PageRequestDTO pageRequestDTO, Model model) {
        if (session.getAttribute("mid") == null) { // 로그인하지 않은 사용자는 로그인 페이지로 이동
            return "redirect:/login";
        }
        // 로그인한 사용자는 마이페이지로 이동
        Long memberId = (Long) session.getAttribute("mid");

        PageResponseDTO responseDTO = myBookListService.getAllMyBooks(pageRequestDTO, memberId);
        MemberDTO memberDTO = memberService.getMemberByMid(memberId);

        model.addAttribute("responseDTO", responseDTO);
        model.addAttribute("pageRequestDTO", pageRequestDTO);
        model.addAttribute("cartDtoList", cartService.getAll(memberId));
        model.addAttribute("memberDTO", memberDTO);
        log.info(memberDTO);

        return "member/mypage";
    }

}