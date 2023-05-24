package com.project.silbaram.dao;

import com.project.silbaram.dto.PageRequestDTO;
import com.project.silbaram.vo.OrderInfoVO;
import com.project.silbaram.vo.OrderListVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderListDAO {
    void insertOrderList(OrderListVO orderListVO); //토스 저장용 구매 정보

    Long selectOid(String orderId);

    List<OrderListVO> selectList(Long memberId, PageRequestDTO pageRequestDTO); // 주문 목록 불러오기

    int getCount(Long memberId); // 전체 주문 목록 수

    void updateOrderListStatus(String orderNum, Long memberId); //주문번호와 가격 총합에 대한 일치 여부 설정

    boolean selectOrderListStatus(Long memberId,String orderNum); //주문번호와 가격 총합에 대한 일치 여부 출력
}
