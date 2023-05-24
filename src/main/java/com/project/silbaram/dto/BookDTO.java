package com.project.silbaram.dto;

import com.project.silbaram.vo.CategoryVO;
import com.project.silbaram.vo.LanguageVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO {
    private Long bkid;
    @NotBlank(message = "책 이름을 입력해주세요.")
    private String name;
    @NotBlank(message = "책 내용을 입력해주세요.")
    private String writer;
    @NotBlank(message = "작가 이름을 입력해주세요.")
    private String publisher;
    @NotNull
    private int price;
    private String synopsis;
    private int pages;
    private String isbnNum;
    @NotNull
    private Long cid;
    private String fileSize;
    @NotNull
    private Long lid;
    private String voiceLength;
    private String bookUrl;
    private String bookTextUrl;
    private String bookImage; //bookUrl과 같이 불러옴
    private String cateName;
    private String language;

    private CategoryVO categoryVO;
    private CategoryDTO categoryDTO;
    private LanguageVO languageVO;
}
