package com.project.silbaram.controller;

import com.project.silbaram.dto.BoardDTO;
import com.project.silbaram.dto.PageRequestDTO;
import com.project.silbaram.dto.PageResponseDTO;
import com.project.silbaram.dto.ReviewDTO;
import com.project.silbaram.service.BoardService;
import com.project.silbaram.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class MyBoardController {

    private final BoardService boardService;
    private final ReviewService reviewService;

    //내가 쓴 문의글 리스트 출력
    @GetMapping("/my_qna")
    public String myQnaList(PageRequestDTO pageRequestDTO, Model model, HttpSession session) {
        log.info("내가 쓴 문의글 리스트: " + pageRequestDTO);
        if (session.getAttribute("mid") == null) { // 로그인하지 않은 사용자는 로그인 페이지로 이동
            return "redirect:/login";
        }

        pageRequestDTO.setPageType("Q");
        String[] types = new String[]{"id"};
        Long mid = (Long) session.getAttribute("mid");

        pageRequestDTO.setMid(mid);
        pageRequestDTO.setTypes(types);
        pageRequestDTO.setKeyword(mid.toString());

        PageResponseDTO<BoardDTO> responseDTO = boardService.myBoardList(pageRequestDTO);

        log.info("responseDTO: {}", responseDTO);
        model.addAttribute("responseDTO", responseDTO);
        model.addAttribute("pageRequestDTO", pageRequestDTO);

        return "/member/my_qna";
    }

    //내가 쓴 요청글 리스트 출력
    @GetMapping("/my_request")
    public String myRequestList(PageRequestDTO pageRequestDTO, Model model, HttpSession session) {
        log.info("내가 쓴 요청글 리스트 " + pageRequestDTO);
        if (session.getAttribute("mid") == null) { // 로그인하지 않은 사용자는 로그인 페이지로 이동
            return "redirect:/login";
        }

        pageRequestDTO.setPageType("R");
        String[] types = new String[]{"id"};
        Long mid = (Long) session.getAttribute("mid");
        log.info("mid : " + mid);

        pageRequestDTO.setMid(mid);
        pageRequestDTO.setTypes(types);
        pageRequestDTO.setKeyword(mid.toString());

        PageResponseDTO<BoardDTO> responseDTO = boardService.myBoardList(pageRequestDTO);
        log.info("responseDTO: {}", responseDTO);
        model.addAttribute("responseDTO", responseDTO);
        model.addAttribute("pageRequestDTO", pageRequestDTO);

        return "/member/my_request";
    }

    //내가 쓴 리뷰 리스트 출력
    @GetMapping("/my_review")
    public String myReviewList(PageRequestDTO pageRequestDTO, Model model, HttpSession session) {
        log.info("내가 쓴 리뷰 리스트 " + pageRequestDTO);
        if (session.getAttribute("mid") == null) { // 로그인하지 않은 사용자는 로그인 페이지로 이동
            return "redirect:/login";
        }

        Long mid = (Long) session.getAttribute("mid");
        pageRequestDTO.setMid(mid);

        PageResponseDTO<ReviewDTO> responseDTO = reviewService.myReviewList(pageRequestDTO);
        log.info("responseDTO: {}", responseDTO);
        model.addAttribute("responseDTO", responseDTO);
        model.addAttribute("pageRequestDTO", pageRequestDTO);

        return "/member/my_review";
    }

    //문의글 QNA 삭제
    @GetMapping("/remove_my_qna")
    public String removeMyQna(Long bdid, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes, HttpSession session) {
        log.info("-------------remove my qna board------------");
        log.info("bdid: " + bdid);
        log.info("bdid: " + pageRequestDTO);

        Long mid = Long.valueOf(session.getAttribute("mid").toString());

        if (mid != null) {
            boardService.remove(bdid, mid);
        }

        return "redirect:/silbaram/member/my_qna";
    }


    //문의글 QNA 삭제
    @PostMapping("/board/remove")
    public String remove(Long bdid, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes, HttpSession session) {
        log.info("-------------remove------------");
        log.info("bdid: " + bdid);
        log.info("bdid: " + pageRequestDTO);

        String mid = session.getAttribute("mid").toString();

        if (mid == null) {
            return "redirect:/silbaram/";
        }

        boardService.remove(bdid, Long.valueOf(mid));

        return "redirect:/member/my_qna";
    }

    //내가 쓴 요청글 삭제
    @GetMapping("/remove_my_request")
    public String removeMyRequest(@RequestParam Long bdid, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes, HttpSession session) {
        log.info("-------------remove my request board------------");
        log.info("bdid: " + bdid);
        log.info("bdid: " + pageRequestDTO);

        Long mid = Long.valueOf(session.getAttribute("mid").toString());

        if (mid != null) {
            boardService.remove(bdid, mid);
        }

        return "redirect:/member/my_request";
    }


    //내가 쓴 리뷰 삭제
    @PostMapping("/review/remove")
    public String remove(Long bkid, Long rid, HttpServletRequest request, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes) {
        log.info("-------------remove------------");
        log.info("rid: " + rid);
        log.info("bkid: " + bkid);
        log.info("rid: " + pageRequestDTO);

        reviewService.remove(rid);
        log.info(" request.getRequestURI(); {}", request.getRequestURI());
        // 요청한 URI : 호스트 뒤의 주소
        //log.info("getRequestURI: " + request.getRequestURI());
        // 요청한 URL : 호스트 전체 주소
        //log.info("getRequestURL: " + request.getRequestURL());
        //  return "redirect:/"+

        return "redirect:/member/my_review";
    }
}