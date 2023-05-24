package com.project.silbaram.service;

import com.project.silbaram.dto.CartDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CartService {

    void registerCart(CartDTO cartDTO); // 카트 추가

    boolean registerCartCheck(Long bkid, Long mid); // 카트 중복 체크(ajax)

    Long getCartOne(Long bookId, Long mid); // 중복 체크 된 카트 아이템 번호 가져오기

    List<CartDTO> getAll(Long mid); // 카트 목록 불러오기

    void remove(Long lkid); // 카트 삭제

    void removeList(Long[] lkids); //카트 다수 삭제
}
