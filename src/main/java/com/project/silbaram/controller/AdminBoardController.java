package com.project.silbaram.controller;

import com.project.silbaram.dto.PageRequestDTO;
import com.project.silbaram.dto.PageResponseDTO;
import com.project.silbaram.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminBoardController {
    private final BoardService boardService;

    @GetMapping("/admin_board_list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("문의글 " + pageRequestDTO);

        //log.info("now {}", boardService.getNow());
        PageResponseDTO responseDTO = boardService.listAll(pageRequestDTO);
        log.info("responseDTO {}", responseDTO);
        model.addAttribute("responseDTO", responseDTO);
        model.addAttribute("pageRequestDTO", pageRequestDTO);
    }
}
