package com.project.silbaram.controller;

import com.project.silbaram.dto.MemberDTO;
import com.project.silbaram.dto.MemberModifyDTO;
import com.project.silbaram.dto.MemberPassWordModifyDTO;
import com.project.silbaram.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MemberModifyController {

    private final MemberServiceImpl memberService;


    @GetMapping("/mypage/membermodify")
    public String memberModifyGET(Model model, HttpSession session) {
        Long mid = (Long) session.getAttribute("mid");
        if (mid == null) { // 로그인하지 않은 사용자는 로그인 페이지로 이동
            return "redirect:/login";
        }
        // 로그인한 사용자는 마이페이지로 이동
        MemberDTO memberDTO = memberService.getMemberByMid(mid); // 회원정보를 조회함
        log.info(memberDTO);

        model.addAttribute("memberDTO", memberDTO);
        return "member/member_modify";
    }

    @PostMapping("/mypage/membermodify")
    public String memberModifyPOST(@Valid MemberModifyDTO memberModifyDTO, MemberDTO memberDTO,
                                   BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session) {
        if (bindingResult.hasErrors()) {
            log.info("has errors...");
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            return "redirect:/mypage/membermodify";
        }

        Long mid = (Long) session.getAttribute("mid");
        memberModifyDTO.setMid(mid);
        session.setAttribute("mid", memberDTO.getMid());
        memberService.modifyMember(memberModifyDTO);
        return "redirect:/mypage/membermodify";
    }

    @GetMapping("/mypage/resetpw")
    public String passwordModifyGET(Model model, HttpSession session) {
        Long mid = (Long) session.getAttribute("mid");
        if (mid == null) { // 로그인하지 않은 사용자는 로그인 페이지로 이동
            return "redirect:/login";
        }
        // 로그인한 사용자는 비밀번호 변경 이동
        MemberDTO memberDTO = memberService.getMemberByMid(mid); // 회원정보를 조회함
        log.info(memberDTO);
        if (memberDTO.getSocialLogin() == null || memberDTO.getSocialLogin() == "") {
            model.addAttribute("memberDTO", memberDTO);
            return "member/reset_pw";
        }
        return "redirect:/mypage";
    }


    @PostMapping("/mypage/resetpw")
    public String checkPwPOST(@RequestParam("oldPassword") String oldPassword,  HttpSession session, MemberDTO memberDTO,
                              RedirectAttributes redirectAttributes, @Valid MemberPassWordModifyDTO memberPassWordModifyDTO,
                              BindingResult bindingResult, Model model) {
        Long mid = (Long) session.getAttribute("mid");
        String DBPassword = String.valueOf((memberService.getMemberByMid(mid).getPassword()));
        if (!DBPassword.equals(oldPassword)) {
            model.addAttribute("msg", "비밀번호를 다시 확인해주세요.");
            // 비밀번호 불일치하는 경우의 처리
            redirectAttributes.addFlashAttribute("error", "비밀번호 불일치");
            return "redirect:/mypage/resetpw";
        }
        else {
            if (bindingResult.hasErrors()) {
                log.info(bindingResult.getAllErrors());
                redirectAttributes.addFlashAttribute("error", "비밀번호 불일치");
                redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
                return "redirect:/mypage/resetpw";
            }
            else {
                // 비밀번호 일치 및 유효성 검사 통과한 경우 비밀번호 변경 처리
                memberPassWordModifyDTO.setMid(mid);
                session.setAttribute("mid", memberDTO.getMid());
                memberService.modifyMemberPw(memberPassWordModifyDTO);
                return "redirect:/mypage/resetpw";
            }
        }
    }
}
