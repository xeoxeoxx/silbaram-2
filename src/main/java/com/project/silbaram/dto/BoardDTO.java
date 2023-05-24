package com.project.silbaram.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDTO {
    //책요청게시판 R, 문의게시판 Q
    private Long bdid;

    private String title;
    //null가능 책요청게시판의 경우 content만 존재

    @NotEmpty
    private String content;

    @NotEmpty
    private String memberId;

    @NotEmpty
    private String pageType;

    //@NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate regDate;

    @NotEmpty
    private String nickname;
}
