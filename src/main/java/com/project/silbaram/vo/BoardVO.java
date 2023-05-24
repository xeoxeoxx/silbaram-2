package com.project.silbaram.vo;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {
    //테이블의 칼럼명과 완전히 같아야함 VO에서는 추가되면 안됨
    private Long bdid;
    private String title;
    private String content;
    private Long memberId;
    private String pageType;
    private LocalDate regDate;
    private String nickname;
}

