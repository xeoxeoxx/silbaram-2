package com.project.silbaram.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

//현재 페이지 번호 page,
// 한 페이지당 보여주는 데이터수 size 가 기본적으로 필요
//@Min @Max 를 이용해서 외부에서 조작하는 것에 대해서도 대비
    @Builder.Default
    @Min(value = 1)
    @Positive
    private int page = 1;

    @Builder.Default
    @Min(value = 10)
    @Max(value = 100)
    @Positive
    private int size = 10;

    private String sortType = "recent";
    private String link;
    private String[] types;
    private String keyword;
    private String pageType;
    private Long mid;
    private String cid;
    private Long bkid;


    //limit에서 사용하는 건너뛰기 skip 의 수를 getSkip() 만들어서 사용
    public int getSkip() {
        return (page - 1) * size;
    }


    //모든 검색, 필터링 조건을 쿼리스트링으로 구성해야 함
    public String getLink() {
        if (link == null) {
            StringBuilder builder = new StringBuilder(); //메모리저장을 위해서
            builder.append("page=" + this.page);
            builder.append("&size=" + this.size);

            if (this.types != null && this.types.length > 0) {
                for (int i = 0; i < this.types.length; i++) {
                    builder.append("&types=" + types[i]);
                }
            }

            if (this.keyword != null) {
                try {
                    builder.append("&keyword=" + URLEncoder.encode(keyword, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            return builder.toString();
        }
        return link;
    }

    //주소에 검색값 따라다니도록
    public boolean checkType(String type) {
        if (this.types == null || this.types.length == 0) {
            return false;
        }
        return Arrays.asList(this.types).contains(type);
    }
}
