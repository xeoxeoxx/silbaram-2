package com.project.silbaram.controller;

import com.project.silbaram.dto.PageRequestDTO;
import com.project.silbaram.dto.PageResponseDTO;
import com.project.silbaram.service.MemberService;
import com.project.silbaram.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminMemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/admin_member_list")
    public void memberList(PageRequestDTO pageRequestDTO, Model model){
        log.info("책 목록 " + pageRequestDTO);

        //log.info("now {}", boardService.getNow());
        PageResponseDTO responseDTO = memberService.list(pageRequestDTO);
        log.info("responseDTO {}", responseDTO);
        model.addAttribute("responseDTO", responseDTO);
        model.addAttribute("pageRequestDTO", pageRequestDTO);
    }
}
