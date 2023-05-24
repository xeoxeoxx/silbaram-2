package com.project.silbaram.service;

import com.project.silbaram.dto.PageRequestDTO;
import com.project.silbaram.dto.PageResponseDTO;
import com.project.silbaram.dto.ReviewDTO;
import com.project.silbaram.vo.ReviewVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ReviewService {
    void register(ReviewDTO reviewDTO);

    ReviewDTO readOne(Long rid);

    void modify(ReviewDTO reviewDTO);

    void remove(Long rid);

    //해당 책 클릭 시 작성된 리뷰들 리스트
//    PageResponseDTO<ReviewDTO> getListOfBoard(Long bkid, PageRequestDTO pageRequestDTO);


    //전체 리뷰 볼수있는 게시판에서 불러올 모든 리뷰 리스트
    PageResponseDTO<ReviewDTO> list(PageRequestDTO pageRequestDTO);

    ReviewDTO selectReviewByMid(Map<String,Object> map);

    List<ReviewDTO> listThree(Long bkid);

    List<ReviewDTO> selectAllByBkid(Long bkid);

    int selectAllCountByBkid(Long bkid);

    PageResponseDTO<ReviewDTO> myReviewList(PageRequestDTO pageRequestDTO);

}
