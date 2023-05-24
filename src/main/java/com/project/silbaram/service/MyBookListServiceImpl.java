package com.project.silbaram.service;

import com.project.silbaram.dao.MyBookListDAO;
import com.project.silbaram.dto.BookDTO;
import com.project.silbaram.dto.OrderListDTO;
import com.project.silbaram.dto.PageRequestDTO;
import com.project.silbaram.dto.PageResponseDTO;
import com.project.silbaram.vo.OrderListVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class MyBookListServiceImpl implements MyBookListService {

    private final MyBookListDAO myBookListDAO;
    private final ModelMapper modelMapper;

    @Override
    public PageResponseDTO<OrderListDTO> getAllMyBooks(PageRequestDTO pageRequestDTO, Long memberId) {
        List<OrderListVO> voList = myBookListDAO.selectAllMyBooks(pageRequestDTO, memberId);
        log.info(voList);
        List<OrderListDTO> dtoList = new ArrayList<>();
        for (OrderListVO orderListVO: voList
             ) {
            OrderListDTO dto =modelMapper.map(orderListVO, OrderListDTO.class); // vo를 dto로 mapping
            dto.setBook(modelMapper.map(orderListVO, BookDTO.class));
            dtoList.add(dto);
        }
        // 전체 갯수
        int total = myBookListDAO.getCount(pageRequestDTO);

        PageResponseDTO<OrderListDTO> pageResponseDTO = PageResponseDTO.<OrderListDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
        return pageResponseDTO;
    }

}

