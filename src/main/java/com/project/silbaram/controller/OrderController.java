package com.project.silbaram.controller;

import com.project.silbaram.dto.PageRequestDTO;
import com.project.silbaram.service.OrderListServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/*
* 주문 리스트 단순 출력용*
* */
@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping(value = "/order")
public class OrderController {

    private final OrderListServiceImpl orderListService;

    @GetMapping("/orderlist")
    public void list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model, HttpSession httpSession){

        if(bindingResult.hasErrors()) {
            pageRequestDTO = pageRequestDTO.builder().build();
        }

        model.addAttribute("responseDTO", orderListService.getList((Long) httpSession.getAttribute("mid"), pageRequestDTO));
        log.info(orderListService.getList((Long) httpSession.getAttribute("mid"), pageRequestDTO));
    }

}
