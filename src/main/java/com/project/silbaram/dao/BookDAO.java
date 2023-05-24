package com.project.silbaram.dao;

import com.project.silbaram.dto.PageRequestDTO;
import com.project.silbaram.vo.BoardVO;
import com.project.silbaram.vo.BookVO;

import java.util.List;
import java.util.Map;

public interface BookDAO {
    void insertBook(BookVO bookVO);
    void updateBook(BookVO bookVO);
    int deleteBookById(Long bkid);
    List<BookVO> getBookList();
    BookVO getBookById(Long bkid);

    List<BookVO> selectList(PageRequestDTO pageRequestDTO);;

    List<BoardVO> selectAll();
    BookVO selectOne(Long bkid);
    List<BookVO> list(PageRequestDTO pageRequestDTO);
    int getCount(PageRequestDTO pageRequestDTO);
    boolean isBuyerByBkIdAndMid(Map<String, Object> map);

    String getCategoryById(String cid);

    int getCountByBookKeyword(String keyword);

    int getCountByWriterKeyword(String keyword);

    List<Map<String, Object>> getBookBycategoryAndCnt(String keyword);

    List<BookVO> adminbookList(PageRequestDTO pageRequestDTO);

    BookVO getOneBookById(Long bkid);

}