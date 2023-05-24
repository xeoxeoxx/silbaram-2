package com.project.silbaram.service;

import com.project.silbaram.dto.OrderInfoDTO;

public interface OrderInfoService {
    void registerOrderInfo(OrderInfoDTO orderInfoDTO); // 토스 저장용 구매 정보

    Long showOid(String orderNum);
}
