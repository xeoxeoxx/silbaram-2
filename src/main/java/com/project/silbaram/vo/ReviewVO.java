package com.project.silbaram.vo;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReviewVO {
    private Long rid;
    private Long mid;
    private String title;
    private boolean reviewLike;
    private int likeCount;
    private String content;
    private LocalDate regDate;
    private Long bkid;

}
