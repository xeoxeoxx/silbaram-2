package com.project.silbaram.vo;

import com.project.silbaram.dto.CategoryDTO;
import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookVO {
    private Long bkid;
    private String name;
    private String writer;
    private String publisher;
    private int price;
    private String synopsis;
    private int pages;
    private String isbnNum;
    private Long cid;
    private String fileSize;;
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

//alter table`book` modify bookImage column varchar(2083);
//alter table`book` rename column language to lid;
//alter table`review` rename column memberId to mid;
//alter table`review` rename column bookId to bkid;
