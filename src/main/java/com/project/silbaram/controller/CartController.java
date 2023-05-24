package com.project.silbaram.controller;

import com.project.silbaram.dto.CartDTO;
import com.project.silbaram.service.CartServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping(value = "/cart")
public class CartController {

    private final CartServiceImpl cartService;

    @PostMapping("/cart_input") //카트 삽입
    public String registerCart(CartDTO cartDTO, HttpSession session){
        cartDTO.setMemberId((Long) session.getAttribute("mid"));
        cartService.registerCart(cartDTO);
        return "redirect:/cart/cart_list";
    }


    @GetMapping("/cart_list")
    public void list(Model model, HttpSession session) { // 카트 목록 표시
        Long mid = (Long) session.getAttribute("mid");

        // 장바구니 리스트 가져오기
        List<CartDTO> dtoList = cartService.getAll(mid);
        model.addAttribute("dtoList", dtoList);

        /* 주문 번호 생성 */
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = currentDate.format(formatter);

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomText = new StringBuilder();
        Random random = new Random();

        int textLength = 4;
        for (int i = 0; i < textLength; i++) {
            int randomIndex = random.nextInt(characters.length());
            randomText.append(characters.charAt(randomIndex));
        }
        String orderId = formattedDate + randomText.toString();
        // model.addAttribute("orderId", orderId);
        session.setAttribute("orderId", orderId);
        /* // 주문 번호 생성 */


        /* 카트 총합 계산 */
        int totalPrice = 0;
        for (CartDTO cartDTO : dtoList) {
            totalPrice += cartDTO.getPrice();
        }
        session.setAttribute("totalPrice", totalPrice);
        /* // 카트 총합 */

    }

    @PostMapping("/deleteCart")
    public String deleteFromCart(Long lkid) { //카트 삭제
        log.info("deleteFromCart.....");
        cartService.remove(lkid);
        return "redirect:/cart/cart_list";
    }

    @PostMapping("/deleteCartList")
    public String deleteCartList(Long[] lkids){ // 카트 다수 삭제
        log.info("deleteCartList.....");
        cartService.removeList(lkids);
        return "redirect:/cart/cart_list";
    }

}
