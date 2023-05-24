package com.project.silbaram.service;

import com.project.silbaram.dto.OrderInfoDTO;
import com.project.silbaram.dto.OrderListDTO;
import com.project.silbaram.dto.PageRequestDTO;
import com.project.silbaram.dto.PageResponseDTO;

public interface OrderListService {

    void registerOrderList(OrderListDTO orderListDTO); // 토스 저장용 구매 정보

    boolean showOrderListStatus(Long memberId,String orderNum); //주문번호와 가격 총합에 대한 일치 여부 출력

    void updateOrderListStatus (String orderNum, Long memberId); //주문번호와 가격 총합에 대한 일치 여부 설정

    PageResponseDTO<OrderListDTO> getList(Long mid, PageRequestDTO pageRequestDTO); // 주문 목록 불러오기
}
