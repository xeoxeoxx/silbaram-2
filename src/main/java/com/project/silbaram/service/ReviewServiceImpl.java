package com.project.silbaram.service;

import com.project.silbaram.dao.ReviewDAO;
import com.project.silbaram.dto.PageRequestDTO;
import com.project.silbaram.dto.PageResponseDTO;
import com.project.silbaram.dto.ReviewDTO;
import com.project.silbaram.vo.ReviewVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewDAO reviewDAO;
    //ModelMapper - dto를 vo로 변환
    private final ModelMapper modelMapper;


    @Override
    public void register(ReviewDTO reviewDTO) {
        log.info(modelMapper);
        log.info("addReview...");

        ReviewVO reviewVO = modelMapper.map(reviewDTO, ReviewVO.class);

        log.info(reviewVO);

        reviewDAO.insert(reviewVO);
    }

    @Override
    public ReviewDTO readOne(Long rid) {
        ReviewVO reviewVO = reviewDAO.selectOne(rid);
        ReviewDTO reviewDTO = modelMapper.map(reviewVO, ReviewDTO.class);
        //vo로 받아서 DTO로 전달

        return reviewDTO;
    }

    @Override
    public void modify(ReviewDTO reviewDTO) {
        ReviewVO reviewVO = modelMapper.map(reviewDTO, ReviewVO.class);
        log.info(reviewVO);
        reviewDAO.update(reviewVO);
    }

    @Override
    public void remove(Long rid) {
        reviewDAO.delete(rid);
    }

    @Override
    public PageResponseDTO<ReviewDTO> list(PageRequestDTO pageRequestDTO) {
        List<ReviewVO> voList = reviewDAO.list(pageRequestDTO);

        List<ReviewDTO> dtoList = new ArrayList<>();
        for (ReviewVO reviewVO : voList) {
            dtoList.add(modelMapper.map(reviewVO, ReviewDTO.class));
        }
        int total = reviewDAO.getCount(pageRequestDTO);

        PageResponseDTO<ReviewDTO> pageResponseDTO = PageResponseDTO.<ReviewDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
        return pageResponseDTO;
    }

    @Override
    public ReviewDTO selectReviewByMid(Map<String, Object> map) {
        ReviewVO reviewVO = reviewDAO.selectReviewByMid(map);
        ReviewDTO reviewDTO = null;
        if (reviewVO != null) {
            reviewDTO = modelMapper.map(reviewVO, ReviewDTO.class);
        }
        return reviewDTO;
    }

    @Override
    public List<ReviewDTO> listThree(Long bkid) {
        List<ReviewVO> voList = reviewDAO.listThree(bkid);

        List<ReviewDTO> dtoList = new ArrayList<>();
        for (ReviewVO reviewVO : voList) {
            dtoList.add(modelMapper.map(reviewVO, ReviewDTO.class));
        }

        return dtoList;
    }

    @Override
    public List<ReviewDTO> selectAllByBkid(Long bkid) {
        List<ReviewVO> voList = reviewDAO.selectAllByBkid(bkid);

        List<ReviewDTO> dtoList = new ArrayList<>();
        for (ReviewVO reviewVO : voList) {
            dtoList.add(modelMapper.map(reviewVO, ReviewDTO.class));
        }

        return dtoList;
    }

    @Override
    public int selectAllCountByBkid(Long bkid) {
        return reviewDAO.selectAllCountByBkid(bkid);
    }


    @Override
    public PageResponseDTO<ReviewDTO> myReviewList(PageRequestDTO pageRequestDTO) {
        log.info("service - myReviewList");
        log.info("skip {}",pageRequestDTO.getSkip());
        log.info("size {}",pageRequestDTO.getSize());

        List<ReviewVO> voList = reviewDAO.myReviewList(pageRequestDTO);
        log.info(voList);

        List<ReviewDTO> dtoList = new ArrayList<>();
        for (ReviewVO reviewVO : voList) {
            dtoList.add(modelMapper.map(reviewVO, ReviewDTO.class));
        }
        int total = reviewDAO.getCountByMid(pageRequestDTO.getMid());

        PageResponseDTO<ReviewDTO> pageResponseDTO = PageResponseDTO.<ReviewDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
        return pageResponseDTO;
    }


}
