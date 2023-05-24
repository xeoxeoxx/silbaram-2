package com.project.silbaram.service;

import com.project.silbaram.dao.OrderListDAO;
import com.project.silbaram.dto.OrderListDTO;
import com.project.silbaram.dto.PageRequestDTO;
import com.project.silbaram.dto.PageResponseDTO;
import com.project.silbaram.vo.OrderListVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderListServiceImpl implements OrderListService {

    private final OrderListDAO orderListDAO;
    private final ModelMapper modelMapper;

    @Override
    public void registerOrderList(OrderListDTO orderListDTO) { //토스 저장용 구매 정보
        OrderListVO orderListVO = modelMapper.map(orderListDTO, OrderListVO.class);
        orderListDAO.insertOrderList(orderListVO);
    }

    @Override
    public boolean showOrderListStatus(Long memberId, String orderNum) { //주문번호와 가격 총합에 대한 일치 여부 출력
        return orderListDAO.selectOrderListStatus(memberId, orderNum);
    }

    @Override
    public void updateOrderListStatus(String orderNum, Long memberId) {  //주문번호와 가격 총합에 대한 일치 여부 설정
        orderListDAO.updateOrderListStatus(orderNum, memberId);
    }

    @Override
    public PageResponseDTO<OrderListDTO> getList(Long mid, PageRequestDTO pageRequestDTO) {
        List<OrderListVO> voList = orderListDAO.selectList(mid ,pageRequestDTO);
        List<OrderListDTO> dtoList = new ArrayList<>();

        for(OrderListVO orderListVO : voList){
            dtoList.add(modelMapper.map(orderListVO, OrderListDTO.class));
        }

        int total = orderListDAO.getCount(mid);
        log.info("total: " + total);

        PageResponseDTO<OrderListDTO> pageResponseDTO = PageResponseDTO.<OrderListDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();

        return pageResponseDTO;
    }
}
