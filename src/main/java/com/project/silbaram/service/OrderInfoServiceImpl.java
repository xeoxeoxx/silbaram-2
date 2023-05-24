package com.project.silbaram.service;

import com.project.silbaram.dao.OrderInfoDAO;
import com.project.silbaram.dto.OrderInfoDTO;
import com.project.silbaram.vo.OrderInfoVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderInfoServiceImpl implements OrderInfoService{

    private final OrderInfoDAO orderInfoDAO;
    private final ModelMapper modelMapper;

    @Override
    public void registerOrderInfo(OrderInfoDTO orderInfoDTO) { //토스 저장용 구매 정보
        OrderInfoVO orderInfoVO = modelMapper.map(orderInfoDTO, OrderInfoVO.class);
        orderInfoDAO.insertOrderInfo(orderInfoVO);
    }

    @Override
    public Long showOid(String orderNum) {
        return orderInfoDAO.selectOid(orderNum);
    }
}
