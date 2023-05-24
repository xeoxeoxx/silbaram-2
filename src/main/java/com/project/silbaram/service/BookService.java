package com.project.silbaram.service;

import com.project.silbaram.dto.BookDTO;
import com.project.silbaram.dto.PageRequestDTO;
import com.project.silbaram.dto.PageResponseDTO;

import java.util.List;
import java.util.Map;

public interface BookService {

    void insertBook(BookDTO bookDTO);

    void updateBook(BookDTO bookDTO);

    void deleteBookById(Long bkid);

    BookDTO getBookById(Long bkid);

    List<BookDTO> getBookList();

    BookDTO readOne(Long bkid);

    PageResponseDTO<BookDTO> list(PageRequestDTO pageRequestDTO);

    boolean isBuyerByBkIdAndMid(Map<String, Object> map);

    String getCategoryById(String cid);

    int getCountByWriterKeyword(String keyword);

    int getCountByBookKeyword(String keyword);

    List<Map<String, Object>> getBookBycategoryAndCnt(String keyword);

    //admin
    void modifyBook(BookDTO bookDTO) ;

    BookDTO readOneBook(Long bkid);

    PageResponseDTO<BookDTO> adminBookList(PageRequestDTO pageRequestDTO);

    BookDTO getOneBookById(Long bkid);
}