package com.project.silbaram.dao;


import com.project.silbaram.dto.PageRequestDTO;
import com.project.silbaram.vo.OrderListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyBookListDAO {

    void insertOrder(OrderListVO orderListVO);

    // 전체조회
    List<OrderListVO> selectAllMyBooks(PageRequestDTO PageRequestDTO, Long memberId);

    int getCount(PageRequestDTO pageRequestDTO);

}
