package com.project.silbaram.controller;

import com.project.silbaram.dto.*;
import com.project.silbaram.service.BookService;
import com.project.silbaram.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Log4j2
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final ReviewService reviewService;

    @GetMapping("/products/list")
    public String list(PageRequestDTO pageRequestDTO, Model model, HttpServletRequest req) {
        String category = "전체";
        int writerTotal = 0;
        int bookTotal = 0;

        if(pageRequestDTO.getCid() != null ){
            category = bookService.getCategoryById(pageRequestDTO.getCid());
        }
        if(pageRequestDTO.getKeyword() != null){
            String keyword = pageRequestDTO.getKeyword();
            writerTotal = bookService.getCountByWriterKeyword(keyword);
            bookTotal = bookService.getCountByBookKeyword(keyword);
            model.addAttribute("categoryCnt", bookService.getBookBycategoryAndCnt(keyword));
        }

        PageResponseDTO responseDTO = bookService.list(pageRequestDTO);

        model.addAttribute("category", category);
        model.addAttribute("writerTotal",writerTotal);
        model.addAttribute("bookTotal",bookTotal);
        model.addAttribute("responseDTO", responseDTO);
        model.addAttribute("pageRequestDTO", pageRequestDTO);

        return "products/list";
    }

    @ResponseBody
    @GetMapping("/products/list_ajax")
    public Map<String, Object> listAjax(PageRequestDTO pageRequestDTO, Model model, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();

        PageResponseDTO responseDTO = bookService.list(pageRequestDTO);
        result.put("responseDTO", responseDTO);
       // result.put("pageRequestDTO", pageRequestDTO);
        return result;
    }

    // 책 상세보기+리뷰조회, 책 상세보기+리뷰 수정 모드 컨트롤러
    @GetMapping("/products/detail")
    public void selectOneBook(@RequestParam(required = false, defaultValue = "false") String isModify, Long bkid, PageRequestDTO pageRequestDTO, Model model, HttpSession session) throws IOException {
        BookDTO bookDTO = bookService.readOne(bkid);
        boolean isBuyer = false;
        boolean isWrite = false;
        String bookImageUrl[] = bookDTO.getBookImage().split("/");
        String bookImageFileName = bookImageUrl[bookImageUrl.length -1];
        bookImageFileName = "/google/image/" + bookImageFileName;

        String bookTxtUrl[] = bookDTO.getBookUrl().split("/");
        String bookTxtFileName = bookTxtUrl[bookTxtUrl.length -1];
        bookTxtFileName = "/synthesize/" + bookTxtFileName;

        Object mid = session.getAttribute("mid");
        ReviewDTO review = new ReviewDTO();
        if(mid != null){
            String id = mid.toString();
            Map<String, Object> map = new HashMap<>();
            // 멤버아이디
            map.put("mid", id);
            // 북아이디
            map.put("bkid", bkid);

            // 구매자인지 멤버아이디와 북아이디로 오더테이블 조회
            isBuyer = bookService.isBuyerByBkIdAndMid(map);
            // 리뷰 데이터가 있는지 멤버아이디와 북아이디로 리뷰 조회
            review = reviewService.selectReviewByMid(map);

            // 리뷰 데이터가 있다면 (널이 아니라면) true
            // 리뷰가 널이라면 false
            isWrite = review != null;

        }
        // 메서드 명만 three 실제로는 다섯개 데이터 가져오도록 함
        List<ReviewDTO> list = reviewService.listThree(bkid);
        model.addAttribute("list", list);
        model.addAttribute("isBuyer", isBuyer);
        model.addAttribute("isWrite", isWrite);
        model.addAttribute("dto", bookDTO);
        model.addAttribute("bookImageFileName", bookImageFileName);
        model.addAttribute("bookTxtFileName", bookTxtFileName);
        model.addAttribute("reviewCnt", reviewService.selectAllCountByBkid(bkid));

        boolean modifyMode = isModify.equals("true");

        if(modifyMode && isWrite){
          model.addAttribute("isModify", isModify);
          model.addAttribute("mdrv", review);
        }
    }





}
