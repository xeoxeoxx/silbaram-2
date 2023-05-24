package com.project.silbaram.controller;

import com.project.silbaram.dto.CartDTO;
import com.project.silbaram.service.CartServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping(value = "/cart")
public class CartRestController {
    private final CartServiceImpl cartService;

    @GetMapping("/cart_input_check")
    public boolean registerCartCheck(@RequestParam("bkid") String bkid, HttpSession httpSession) { // 카트 중복 체크(ajax)
        log.info("/cart_input_check");
        return cartService.registerCartCheck(Long.parseLong(bkid), (Long) httpSession.getAttribute("mid"));
    }

    @GetMapping("/cart_deleteCart_check")
    public void deleteFromCart(@RequestParam("bkid") String bkid, HttpSession httpSession) { //카트 삭제
        cartService.remove(cartService.getCartOne(Long.parseLong(bkid), (Long) httpSession.getAttribute("mid")));
    }

    @GetMapping("/cart_inputCart_check") //카트 삽입
    public void registerCart(@RequestParam("bkid") String bkid, @Validated CartDTO cartDTO, HttpSession httpSession, BindingResult bindingResult){
        log.info("/cart_inputCart_check");

        // 카트 중복 검사
        if (cartService.registerCartCheck(Long.parseLong(bkid), (Long) httpSession.getAttribute("mid"))){
            log.info("중복입니다..!!");
            bindingResult.rejectValue("bookId", "duplicate", "Duplicate bookId");
        }else{ // 중복이 아니면 카트 추가
            cartDTO.setBookId(Long.parseLong(bkid));
            cartDTO.setMemberId((Long) httpSession.getAttribute("mid"));
            cartService.registerCart(cartDTO);
        }
    }



}
