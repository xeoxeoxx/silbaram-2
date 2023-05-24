package com.project.silbaram.controller;

import com.project.silbaram.dto.*;
import com.project.silbaram.service.BoardService;
import com.project.silbaram.service.BookService;
import com.project.silbaram.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    /*
            ###책요청게시판 (한줄게시판)--페이지타입 'R'
        등록-로그인한 사람만 (완)
        수정기능X
        삭제-자신이 쓴 글인경우 글 (완)
        옆에 삭제버튼
        목록-상세페이지X, 페이징 처리 (완)

    ###문의게시판(기본 게시판)-페이지타입 'Q
        등록-로그인한 사람만 (완료)
        목록-페이징처리 (완료)
        수정-자신이 쓴 글일 경우에만 상세페이지에서 버튼 나타남 (완료)
        삭제-자기가 쓴 글일 경우에만 상세페이지에서삭 제 버튼 나타남 (완료)


     */
    //문의글 리스트
    @GetMapping("/list_qna")
    public String list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("문의글 " + pageRequestDTO);
        // 게시판 타입 지정
        pageRequestDTO.setPageType("Q");

        PageResponseDTO responseDTO = boardService.list(pageRequestDTO);
        log.info("responseDTO {}", responseDTO);
        model.addAttribute("responseDTO", responseDTO);
        model.addAttribute("pageRequestDTO", pageRequestDTO);

        return "redirect:/board/list_qna";
    }


    //문의글 get / post
    @GetMapping("/add_qna")
    public void registerQnAGET(Model model, HttpSession session) {
        Object mid = session.getAttribute("mid");
        if(mid != null){
            Long id = Long.valueOf(mid.toString());
            MemberDTO memberDTO =  memberService.getMemberByMid(id);
            model.addAttribute("nickname", memberDTO.getNickName());
        }
        log.info("GET QnA board register");
    }

    @PostMapping("/add_qna")
    public String registerQnaPOST(@Valid BoardDTO boardDTO, BindingResult bindingResult
            , RedirectAttributes redirectAttributes, HttpSession session) {
        log.info("POST qna register...");

        if (bindingResult.hasErrors()) {
            log.info("qna register has error...");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/board/add_qna";
        }

        // 컨트롤러 단에서도 세션값 기반으로 작성자 값 세팅
        Long mid = Long.valueOf(session.getAttribute("mid").toString());
        boardDTO.setMemberId(String.valueOf(mid));
        boardService.register(boardDTO);
        return "redirect:/board/list_qna";
    }


    //책 요청글 get / post
    @GetMapping("/request_book_list")
    public String registerRequestGET(PageRequestDTO pageRequestDTO, Model model) {
        log.info("책 요청 리스트" + pageRequestDTO);
        // 게시판 타입 지정
        pageRequestDTO.setPageType("R");

        PageResponseDTO responseDTO = boardService.list(pageRequestDTO);
        log.info("responseDTO {}", responseDTO);
        model.addAttribute("responseDTO", responseDTO);
        model.addAttribute("pageRequestDTO", pageRequestDTO);

        return "board/request_book_list";
    }

    @PostMapping("/request_book_list")
    public String registerRequestPOST(BoardDTO boardDTO, BindingResult bindingResult
            , RedirectAttributes redirectAttributes, HttpSession session) {
        log.info("POST request register...");

        Long mid = Long.valueOf(session.getAttribute("mid").toString());

        if (bindingResult.hasErrors() || mid == null) {
            log.info("Request register has error...");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/board/request_book_list";
        }
        boardDTO.setPageType("R");
        boardDTO.setMemberId(String.valueOf(mid));

        log.info(boardDTO);

        boardService.register(boardDTO);
        return "redirect:/board/request_book_list";
    }


    @GetMapping({"/read_qna", "/modify_qna"})
    public String read(Long bdid, PageRequestDTO pageRequestDTO, Model model) {
        BoardDTO boardDTO = boardService.readOne(bdid);
        log.info(boardDTO);

        model.addAttribute("dto", boardDTO);

        return "redirect:/member/modify_qna?bdid=" + bdid;
    }

    @PostMapping("/modify_qna")
    public String modify(PageRequestDTO pageRequestDTO, @Valid BoardDTO boardDTO, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request) { //유효성 검가 결과 에러가 있으면 수정페이지로 돌아감
        log.info("----------modify---------");

        Long mid = Long.valueOf(session.getAttribute("mid").toString());

        boardDTO.setMemberId(String.valueOf(mid));

        if (bindingResult.hasErrors() || mid == null) {
            log.info("modify has error...");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("bdid", boardDTO.getBdid());
            return "redirect:/board/modify_qna";
        }

        boardService.modify(boardDTO);

        log.info("request.getRequestURI() {}",request.getRequestURI());
        redirectAttributes.addAttribute("bdid", boardDTO.getBdid());

        return "redirect:/board/read_qna";
    }

    @PostMapping("/remove")
    public String remove(Long bdid, HttpServletRequest request, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes, HttpSession session) {
        log.info("-------------remove------------");
        log.info("bdid: " + bdid);
        log.info("bdid: " + pageRequestDTO);

        // 요청한 URI : 호스트 뒤의 주소
        //log.info("getRequestURI: " + request.getRequestURI());
        // 요청한 URL : 호스트 전체 주소
        //log.info("getRequestURL: " + request.getRequestURL());

        String mid = session.getAttribute("mid").toString();

        if (mid == null) {
            return "redirect:/board/modify_qna";
        }


        boardService.remove(bdid, Long.valueOf(mid));
        //return "redirect:/"+ request.getRequestURI();
        return "redirect:/board/list_qna?" + pageRequestDTO.getLink();
    }

    @GetMapping("/remove_request_book")
    public String removeRequestBook(Long bdid, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes, HttpSession session) {
        log.info("-------------remove request book------------");
        log.info("bdid: " + bdid);
        log.info("bdid: " + pageRequestDTO);

        Long mid = Long.valueOf(session.getAttribute("mid").toString());

        if (mid != null) {
            boardService.remove(bdid, mid);
        }

        return "redirect:/board/request_book_list";
    }


}
