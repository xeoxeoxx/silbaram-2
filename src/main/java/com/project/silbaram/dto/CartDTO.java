package com.project.silbaram.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDTO {
    private Long lkid;
    private Long bookId;
    private Long memberId;

    // 테이블 조인으로 가져와야 할 값들
    private String name;
    private String writer;
    private int price;

    //다수 카트 아이템 삭제 mybatis를 위함
    private Long[] lkids;

    private int totalPrice; //총합;

}
