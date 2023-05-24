package com.project.silbaram.vo;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CartVO {
    private Long lkid; // 카트 번호
    private Long bookId;
    private Long memberId;

    // 테이블 조인으로 가져와야 할 값들
    private String name;
    private String writer;
    private int price;
}
