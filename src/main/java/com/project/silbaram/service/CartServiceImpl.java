package com.project.silbaram.service;

import com.project.silbaram.dao.CartDAO;
import com.project.silbaram.dto.CartDTO;
import com.project.silbaram.vo.CartVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

    private final CartDAO cartDAO; // 나를 위한 메모:todoMapper와 같은 것.
    private final ModelMapper modelMapper;

    @Override
    public void registerCart(CartDTO cartDTO) { // 카트 추가
        log.info("modelMapper: " + modelMapper);
        CartVO cartVO = modelMapper.map(cartDTO, CartVO.class);
        cartDAO.insertCart(cartVO);
        log.info("CartServiceImpl bookid: " + cartVO.getBookId());
    }

    @Override
    public boolean registerCartCheck(Long bkid, Long mid) {// 카트 중복 체크(ajax)
        int check = cartDAO.insertCartCheck(bkid, mid);
        if(check > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Long getCartOne(Long bookId, Long mid) {// 중복 체크 된 카트 아이템 번호 가져오기
        return cartDAO.selectCartOne(bookId, mid);
    }



    @Override
    public List<CartDTO> getAll(Long mid) { // 카트 목록 불러오기
        List<CartVO> voList = cartDAO.selectAll(mid);
        List<CartDTO> dtoList = new ArrayList<>();
        for(CartVO cartVO : voList){
            CartDTO cartDTO = modelMapper.map(cartVO, CartDTO.class);
            dtoList.add(cartDTO);
        }
        return dtoList;
    }

    @Override
    public void remove(Long lkid) { //카트 삭제
        cartDAO.delete(lkid);
    }

    @Override
    public void removeList(Long[] lkids) { //카트 다수 삭제
        CartDTO cartDTO = new CartDTO();
        cartDTO.setLkids(lkids);
        cartDAO.deleteCartList(lkids);
    }

}
