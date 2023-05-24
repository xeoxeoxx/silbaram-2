package com.project.silbaram.controller;

import com.project.silbaram.dto.PageRequestDTO;
import com.project.silbaram.dto.PageResponseDTO;
import com.project.silbaram.dto.ReviewDTO;
import com.project.silbaram.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/review")
@Log4j2
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping({"/list", "/total"})
    public void list(PageRequestDTO pageRequestDTO, Model model, @RequestParam Long bkid) {
        //PageResponseDTO responseDTO = reviewService.list(pageRequestDTO);
        //log.info(responseDTO);
        List<ReviewDTO> list = reviewService.selectAllByBkid(bkid);
        log.info(list);
        model.addAttribute("total", reviewService.selectAllCountByBkid(bkid));
        model.addAttribute("list", list);
    }

    //@RequestMapping(value = "/register", method = RequestMethod.GET)
    @GetMapping("/register")
    public void registerGET() {
        log.info("GET review register...");
    }

    @PostMapping("/register")
    public String registerPOST(@Valid ReviewDTO reviewDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("POST review register...");

        if (bindingResult.hasErrors()) {
            log.info("review register has error...");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/silbaram/products/detail?bkid="+reviewDTO.getBkid();
        }
        log.info("REVIEW", reviewDTO);
        reviewService.register(reviewDTO);
        return "redirect:/silbaram/products/detail?bkid="+reviewDTO.getBkid();
    }

    @GetMapping( "/modify")
    public void read(Long rid, PageRequestDTO pageRequestDTO, Model model) {
        ReviewDTO reviewDTO = reviewService.readOne(rid);
        log.info("review {}", reviewDTO);

        model.addAttribute("dto", reviewDTO);
    }



    @PostMapping("/modify")
    public String modify(PageRequestDTO pageRequestDTO, @Valid ReviewDTO reviewDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("----------modify--------");
        if (bindingResult.hasErrors()) {
            log.info("modify has error...");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("rid", reviewDTO.getRid()); //tno가 쿼리스트링(like '?xx=1')으로 전달
            return "redirect:/silbaram/review/modify";
        }
        log.info(reviewDTO);
        reviewService.modify(reviewDTO);

       // redirectAttributes.addAttribute("rid", reviewDTO.getRid());

        return "redirect:/silbaram/products/detail?bkid="+reviewDTO.getBkid();
       // return "redirect:/silbaram/products/list";
    }

    @PostMapping("/remove")
    public String remove(Long bkid, Long rid, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes) {
        log.info("-------------remove------------");
        log.info("rid: " + rid);
        log.info("bkid: " + bkid);
        log.info("rid: " + pageRequestDTO);

        reviewService.remove(rid);

        return "redirect:/silbaram/products/detail?bkid="+ bkid;
    }

    @ResponseBody
    @GetMapping("/list_ajax")
    public List<ReviewDTO> listAjax(@RequestParam Long bkid, @RequestParam int page) {
        //PageResponseDTO responseDTO = reviewService.list(pageRequestDTO);
        //log.info(responseDTO);

        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        pageRequestDTO.setPage(page);
        pageRequestDTO.setBkid(bkid);
        log.info(pageRequestDTO);
        PageResponseDTO<ReviewDTO> list = reviewService.list(pageRequestDTO);
        log.info(list);
        return list.getDtoList();
       // model.addAttribute("total", reviewService.selectAllCountByBkid(bkid));
       // model.addAttribute("list", list);
    }



}
