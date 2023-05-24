package com.project.silbaram.service;

import com.project.silbaram.dao.BoardDAO;
import com.project.silbaram.dto.BoardDTO;
import com.project.silbaram.dto.PageRequestDTO;
import com.project.silbaram.dto.PageResponseDTO;
import com.project.silbaram.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardDAO boardDAO;
    //ModelMapper - dto를 vo로 변환
    private final ModelMapper modelMapper;


    @Override
    public void register(BoardDTO boardDTO) {
        log.info(modelMapper);
        log.info("addBoard...");

        BoardVO boardVO = modelMapper.map(boardDTO, BoardVO.class);

        log.info(boardVO);

        boardDAO.insert(boardVO);
    }

    @Override
    public BoardDTO readOne(Long bdid) {
        BoardVO boardVO = boardDAO.selectOne(bdid);
        BoardDTO boardDTO = modelMapper.map(boardVO, BoardDTO.class);
        //vo로 받아서 DTO로 전달

        return boardDTO;
    }

    @Override
    public void modify(BoardDTO boardDTO) {
        //vo객체를 생성하면서 매개변수로 받은 (값이 입력되어 있는) dto와 vo를 매핑(값을 넣음)을 해서 vo에 값을 입력
        BoardVO boardVO = modelMapper.map(boardDTO, BoardVO.class);

        boardDAO.update(boardVO);
    }

    @Override
    public void remove(Long bdid, Long mid) {
        BoardVO boardVO = boardDAO.selectOne(bdid);
        Long memberId = boardVO.getMemberId();
        // bdid로 구해온 보드 데이터 내의 작성자 값과 세션 내의 유저 값 비교
        if(memberId.equals(mid)){
            log.info("delete!");
            boardDAO.delete(bdid);
        }

    }


    @Override
    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO) {
        log.info("service - list");
        log.info("skip {}",pageRequestDTO.getSkip());
        log.info("size {}",pageRequestDTO.getSize());
        // !! 조건 검색 쿼리로 변경
        // List<BoardVO> voList = boardDAO.selectAll();
        List<BoardVO> voList = boardDAO.list(pageRequestDTO);
        log.info(voList);

        List<BoardDTO> dtoList = new ArrayList<>();
        for (BoardVO boardVO : voList) {
            dtoList.add(modelMapper.map(boardVO, BoardDTO.class));
        }
        int total = boardDAO.getCount(pageRequestDTO);

        PageResponseDTO<BoardDTO> pageResponseDTO = PageResponseDTO.<BoardDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
        return pageResponseDTO;

    }

//내가쓴 게시글
    @Override
    public PageResponseDTO<BoardDTO> myBoardList(PageRequestDTO pageRequestDTO) {
        log.info("service - myBoardList");
        log.info("skip {}", pageRequestDTO.getSkip());
        log.info("size {}", pageRequestDTO.getSize());

        List<BoardVO> voList = boardDAO.myBoardList(pageRequestDTO);
        log.info(voList);

        List<BoardDTO> dtoList = new ArrayList<>();
        for (BoardVO boardVO : voList) {
            dtoList.add(modelMapper.map(boardVO, BoardDTO.class));
        }
        int total = boardDAO.getCount(pageRequestDTO);

        PageResponseDTO<BoardDTO> pageResponseDTO = PageResponseDTO.<BoardDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
        return pageResponseDTO;
    }

    @Override
    public String getNow() {
        return boardDAO.getNow();
    }

    @Override
    public PageResponseDTO<BoardDTO> listAll(PageRequestDTO pageRequestDTO) {
        log.info("service - list");
        // !! 조건 검색 쿼리로 변경
        // List<BoardVO> voList = boardDAO.selectAll();
        List<BoardVO> voList = boardDAO.listAll(pageRequestDTO);
        log.info(voList);
        List<BoardDTO> dtoList = new ArrayList<>();
        for (BoardVO boardVO : voList) {
            dtoList.add(modelMapper.map(boardVO, BoardDTO.class));
        }
        int total = boardDAO.getCount(pageRequestDTO);

        PageResponseDTO<BoardDTO> pageResponseDTO = PageResponseDTO.<BoardDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
        return pageResponseDTO;

    }


}
