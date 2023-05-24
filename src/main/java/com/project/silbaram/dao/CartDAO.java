package com.project.silbaram.dao;

import com.project.silbaram.vo.CartVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface CartDAO {

    void insertCart(CartVO cartVO); // 카트 추가

    int insertCartCheck(Long bookId, Long memberId); // 카트 중복 체크(ajax)

    Long selectCartOne(Long bookId, Long memberId); // 중복 체크 된 카트 아이템 번호 가져오기

    List<CartVO> selectAll(Long memberId); // 카트 목록 불러오기

    void delete(Long lkid); //카트 삭제

    void deleteCartList(Long[] lkids); //카트 다수 삭제

}
