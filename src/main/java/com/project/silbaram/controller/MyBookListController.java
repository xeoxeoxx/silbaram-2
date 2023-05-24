package com.project.silbaram.controller;

import com.project.silbaram.dto.PageRequestDTO;
import com.project.silbaram.dto.PageResponseDTO;
import com.project.silbaram.service.MyBookListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyBookListController {

    private final MyBookListService myBookListService;

    @GetMapping("/mybooks")
    public String MyBookListGET(Model model, HttpSession session, PageRequestDTO pageRequestDTO) {

        Long memberId = (Long) session.getAttribute("mid");

        PageResponseDTO responseDTO = myBookListService.getAllMyBooks(pageRequestDTO, memberId);
        log.info("responseDTO : " + responseDTO);
        log.info("responseDTO.getDtoList() {} " + responseDTO.getDtoList());

        model.addAttribute("responseDTO", responseDTO);
        model.addAttribute("pageRequestDTO", pageRequestDTO);

        return "member/my_book_list";
    }

}
