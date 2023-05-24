package com.project.silbaram.dao_test;


import com.project.silbaram.dao.CartDAO;
import com.project.silbaram.dto.CartDTO;
import com.project.silbaram.service.CartServiceImpl;
import com.project.silbaram.vo.CartVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Log4j2
public class CartMapperTests {

    @Autowired
    private CartDAO cartDAO;

    @Autowired
    private CartServiceImpl cartService;


    /*@Test
    public void testInsertCart() { // 카트 삽입 테스트
        CartVO cartVO = CartVO.builder()
                .memberId(1002)
                .bookId(2008)
                .build();
        cartDAO.insertCart(cartVO);
    }*/

    // 카트 리스트 불러오기
    /*@Test
    public void testSelectAll() {
        List<CartVO> voList = cartDAO.selectAll();
        voList.forEach(vo -> log.info(vo));
        log.info("totalPrice: " + cartDAO.getTotalPrice(1001)); //카트 총합 가격 계산하기
    }*/

    @Test
    public void getAllTests() { // 카트 목록 불러오기 테스트 (service)
        List<CartDTO> cartDTOS = cartService.getAll(1001L);
        log.info("cartDTOS: " + cartDTOS);
    }

    @Test
    public void selectAllTest() { // 카트 목록 불러오기 테스트 (dao)
        List<CartVO> cartDTOS = cartDAO.selectAll(1001L);
        log.info("cartDTOS: " + cartDTOS);
    }

    @Test
    public void deleteTests() { // 카트 삭제 테스트
//        cartDAO.delete(6139);
        cartService.remove(6140L);
    }

    @Test
    public void deleteListTests() { //카트 다수 삭제 테스트
        Long[] lkids = {6135L, 6136L, 6137L};
        CartDTO cartDTO = CartDTO.builder()
                .lkids(lkids)
                .build();
        log.info("lkids: " + cartDTO.getLkids());
        cartService.removeList(cartDTO.getLkids());
    }
}
