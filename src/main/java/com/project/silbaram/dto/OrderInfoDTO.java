package com.project.silbaram.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
 * 기존 orderlist테이블.
 * 화면에 주문목록을 표시하기 위한 이름이 같은 다른 테이블과 혼동x;
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderInfoDTO {
    private Long ogid; // 주문번호 (!= orderNum)
    private Long bookId;
    private String bookName;
    private int bookPrice;
    private Long oid; //OrderList조인용
    private String orderNum;
    private Long memberId;
    private boolean status;

}
