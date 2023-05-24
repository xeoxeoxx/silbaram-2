package com.project.silbaram.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {
    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;


        this.end = (int) (Math.ceil(this.page / 10.0)) * 10;
        this.start = this.end - 9;

        int last = (int) (Math.ceil((total / (double) size)));

        this.end = end > last ? last : end;


        this.prev = this.start > 1;
        this.next = total > this.end * this.size;

        this.startNo = Long.valueOf(total - (this.page - 1) * size);
    }

    private int page;

    private int size;
    //한 페이지에 나올 게시글 갯수

    private int total;

    //시작 페이지 번호
    private int start;
    //끝 페이지 번호
    private int end;

    //이전 페이지의 존재 여부
    private boolean prev;
    //다음 페이지의 존재 여부
    private boolean next;

    private List<E> dtoList;

    private Long startNo;

}
//화면상에서 페이지 번호들을 출력하려면 현재페이지 번호와 데이터 수 이용해서 계산할 필요가 있음
//그래서 pageRequestDTO 생성자를 통해서 필요한 page나 size등을 전달받도록

//제네릭 <E> : 다른 종류의 객체를 이용해서 PageResponseDTO 구성할수 있도록 하기위해
//게시판이나 뢰원정보 등도 페이징 필요할 수 있기때문에 공통적인 처리를 위해서 제네릭으로 구성
