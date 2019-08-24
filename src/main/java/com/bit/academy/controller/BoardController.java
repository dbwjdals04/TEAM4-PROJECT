package com.bit.academy.controller;

import com.bit.academy.model.BoardPaging;
import com.bit.academy.model.BoardVO;
import com.bit.academy.service.BoardService;
import com.bit.academy.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private ProductService productService;


//    @RequestMapping(value = "/board/insert", method = RequestMethod.GET)
    @GetMapping("/board/insert")
    public String boardInsert(){

        return "board/insert";
    }



    @PostMapping("/board/insert")
    public String boardInsertExecute(Model model, @ModelAttribute BoardVO boardVO){

        log.debug("##### 게시물 등록 요청 처리 ####");
        log.debug(boardVO.toString());
        this.boardService.insertBoard(boardVO);

        log.debug("##### 게시물 등록 결과 ####");
        log.debug(boardVO.toString());

        boardVO = this.boardService.selectBoard(boardVO.getQna_no());

        model.addAttribute("board", boardVO);

        return "board/insert_after";
    }

    @PostMapping("/board/reply/{qna_no}")
    public String replyInsert(Model model, @PathVariable int qna_no){
        model.addAttribute("board", this.boardService.selectBoard(qna_no));
        return "board/reply";
    }
    @PostMapping("/board/reply")
    public String boardreplyExecute(Model model, @ModelAttribute BoardVO boardVO){
        log.debug("##### 답변 등록 요청 처리 ####");
        log.debug(boardVO.toString());
        this.boardService.replyBoard(boardVO);

        log.debug("##### 답변 등록 결과 ####");
        log.debug(boardVO.toString());

        boardVO = this.boardService.selectBoard(boardVO.getQna_no());

        model.addAttribute("board", boardVO);

        return "board/reply_after";
    }


    @PostMapping("/board/view/{qna_no}")
        public String boardView(Model model, @PathVariable int qna_no){


        model.addAttribute("board", this.boardService.selectBoard(qna_no));
        return "board/view";
    }



    @PostMapping("/board/delete/{qna_no}")
    public String boardDelete(HttpServletRequest request, @PathVariable int qna_no){

        this.boardService.deleteBoard(request, qna_no);
        return "board/delete_after";
    }

    @PostMapping("/board/update/{qna_no}")
    public String boardUpdate(Model model, @PathVariable int qna_no){
        model.addAttribute("board", this.boardService.selectBoard(qna_no));
        return "board/update";
    }

    @PostMapping("/board/update")
    public String boardUpdateExecute(Model model, @ModelAttribute BoardVO boardVO){
        this.boardService.updateBoard(boardVO);
        model.addAttribute("board", this.boardService.selectBoard(boardVO.getQna_no()));
        return "board/insert_after";
    }

    @GetMapping("/board/list")
    public String boardList(Model model, @ModelAttribute BoardPaging boardPaging){
        log.debug("############## 리스트 확인 ##########");
        log.debug(boardPaging.toString());
        model.addAllAttributes(this.boardService.selectBoardList(boardPaging));
        return "board/list";
    }

    @GetMapping("/postcode")
    public String posrtocde(){
        return "postcode";
    }

    @ResponseBody
    @GetMapping("/board/passck")
    public Map<String,Object> passck(Model model, @ModelAttribute BoardVO boardVO){

        log.debug("############## 비밀번호 확인 ##########");
        log.debug(boardVO.toString());

        Map<String,Object> map = new HashMap<>();
        map.put("count", this.boardService.passck(boardVO));

        return map;
    }

    @GetMapping("/main/index")
    public String index(Model model) {
        model.addAttribute("product",this.productService.productMain());
        log.debug(String.valueOf(this.productService.productMain()));
        return "main/index";
    }

    @ResponseBody
    @GetMapping("/board/idchk")
    public Map<String,Object> idchk(Model model, @ModelAttribute BoardVO boardVO){

        log.debug("############## 아이디 중복체크 확인 ##########");
        log.debug(boardVO.toString());

        Map<String,Object> map = new HashMap<>();
        map.put("count", this.boardService.idchk(boardVO));

        return map;
    }
}
