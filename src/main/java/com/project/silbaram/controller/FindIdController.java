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
public class FindIdController {

    private  final MemberService memberService;

    @GetMapping("/findid")
    public String findIdGET (){
        return "member/find_id";
    }


    @PostMapping("/findid")
    public String findIdPOST(@RequestParam String checkEmail, Model model) {
        try {
            MemberDTO memberDTO = memberService.getUserIdByEmail(checkEmail);
            if (memberDTO.getUserId() != null && memberDTO !=null) {
                model.addAttribute("userId", memberDTO.getUserId());
                return "member/find_id_result";
            } else {
                model.addAttribute("msg", "존재하지 않는 이메일");
                return "member/find_id_fail";
            }
        } catch (Exception e) {
            // 예외 처리
            model.addAttribute("msg", "오류가 발생했습니다.");
            return "member/find_id_fail";
        }
    }


}
