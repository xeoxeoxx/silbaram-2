package com.project.silbaram.controller;

import com.project.silbaram.dto.MemberDTO;
import com.project.silbaram.dto.MemberPassWordModifyDTO;
import com.project.silbaram.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/")
public class MemberQuitController {

    private final MemberServiceImpl memberService;

    @GetMapping("/mypage/memberquit")
    public String passwordModifyGET(Model model, HttpSession session) {
        Long mid = (Long) session.getAttribute("mid");
        if (mid == null) { // 로그인하지 않은 사용자는 로그인 페이지로 이동
            return "redirect:/login";
        }

        MemberDTO memberDTO = memberService.getMemberByMid(mid); // 회원정보를 조회함
        log.info(memberDTO);
        model.addAttribute("memberDTO", memberDTO);
        return "member/member_quit";
    }

    @PostMapping("/mypage/memberquit")
    public String checkPwPOST(@RequestParam("oldPassword") String oldPassword, HttpSession session, Model model,
                              MemberDTO memberDTO,RedirectAttributes redirectAttributes, BindingResult bindingResult) {
        Long mid = (Long) session.getAttribute("mid");
        String DBPassword = String.valueOf((memberService.getMemberByMid(mid).getPassword()));
        if (!DBPassword.equals(oldPassword)) {
            model.addAttribute("msg", "비밀번호를 다시 확인해주세요.");
            // 비밀번호 불일치하는 경우의 처리
            redirectAttributes.addFlashAttribute("error", "비밀번호 불일치");
            return "member/member_quit";
        }
        else {
            if (bindingResult.hasErrors()) {
                log.info(bindingResult.getAllErrors());
                model.addAttribute("msg", "회원탈퇴 실패");
                redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
                return "member/member_quit";
            }
            else {

                memberDTO.setMid(mid);
                session.setAttribute("mid", memberDTO.getMid());
                model.addAttribute("msg", "회원탈퇴 처리가 완료되었습니다.");
                memberService.quitMember(memberDTO);
                return "member/member_quit_complete";
            }
        }
    }
}