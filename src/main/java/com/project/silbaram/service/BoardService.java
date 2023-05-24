package com.project.silbaram.service;

import com.project.silbaram.dto.BoardDTO;
import com.project.silbaram.dto.PageRequestDTO;
import com.project.silbaram.dto.PageResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface BoardService {

    void register(BoardDTO boardDTO);

    BoardDTO readOne(Long bdid);

    void modify(BoardDTO boardDTO);

    void remove(Long bdid, Long mid);

//  목록/검색 기능
    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

    PageResponseDTO<BoardDTO> myBoardList(PageRequestDTO pageRequestDTO);

    String getNow();


//    admin

    PageResponseDTO<BoardDTO> listAll(PageRequestDTO pageRequestDTO);

}
