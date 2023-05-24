package com.project.silbaram.controller;

import com.project.silbaram.dto.MemberDTO;
import com.project.silbaram.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/")
public class FindPwController {

    private  final MemberService memberService;

    @GetMapping("/findpw")
    public String findPwGET (){
        return "member/find_pw";
    }


    @PostMapping("/findpw")
    public String findPwPOST(@RequestParam String password, @RequestParam String email,
                             @RequestParam String userId, Model model) {
        try {
            boolean success = memberService.modifyMemberPwByEmailAndUserId(password, email, userId);
            log.info(success);
            if (success == true) {
                model.addAttribute("password", password);
                model.addAttribute("email", email);
                model.addAttribute("userId", userId);
                model.addAttribute("msg", "비밀번호 변경에 성공하였습니다.");
                return "member/login";
            } else {
                model.addAttribute("msg", "비밀번호 변경에 실패하였습니다.");
                return "member/find_pw_fail";
            }
        } catch (Exception e) {
            // 예외 처리
            model.addAttribute("msg", "오류가 발생했습니다.");
            return "member/find_pw_fail";
        }
    }


}
