package com.project.silbaram.dao_test;

import com.project.silbaram.dao.BoardDAO;
import com.project.silbaram.dto.PageRequestDTO;
import com.project.silbaram.vo.BoardVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Log4j2
public class BoardMapperTests {

    @Autowired
    private BoardDAO boardDAO;

    @Test
    public void testGetTime() {
        log.info(boardDAO.getNow());
    }

    @Test
    public void testInsert() {
        for(int i = 0; i<=100; i++) {
            BoardVO boardVO = BoardVO.builder()
                    .title("RequestBookBoardTest")
                    .content("문의1")
                    .memberId(1L)
                    .pageType("Q")
                    .regDate(LocalDate.of(2023, 05, 06))
                    .build();
            boardDAO.insert(boardVO);
        }
    }

    @Test
    public void testSelectAll() {
        List<BoardVO> boardVOS = boardDAO.selectAll();
        for (BoardVO boardVO : boardVOS) {
            log.info(boardVO);
        }
    }

    @Test
    public void testSelectOne() {
        BoardVO boardVO = boardDAO.selectOne(5001L);
        log.info(boardVO);
    }

    @Test
    public void testSelectList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();
        List<BoardVO> voList = boardDAO.list(pageRequestDTO);
        voList.forEach(vo -> log.info(vo));
    }


    @Test
    public void testDelete() {
        List<BoardVO> list = boardDAO.selectAll();
        list.forEach(vo -> System.out.println(vo));

        boardDAO.delete(5001L);

        //삭제 확인
        list = boardDAO.selectAll();
        list.forEach(vo -> System.out.println(vo));
    }

    @Test
    public void testUpdate() {
        BoardVO boardVO = BoardVO.builder()
                .bdid(5001L)
                .title("테스트업데이트")
                .content("UpdateTest")
                .memberId(1001L)
                .pageType("Q")
                .regDate(LocalDate.of(2023, 05, 04))
                .build();

        boardDAO.update(boardVO);
    }


}
