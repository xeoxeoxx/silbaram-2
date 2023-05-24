package com.project.silbaram.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDTO {

    private Long rid;

//    @NotEmpty
    private Long mid;

  //  @NotEmpty
    private String title;

    private boolean reviewLike;

    @NotEmpty
    private String content;

   // @NotEmpty
    private Long bkid;

    private LocalDate regDate;

    private String nickname;
}
