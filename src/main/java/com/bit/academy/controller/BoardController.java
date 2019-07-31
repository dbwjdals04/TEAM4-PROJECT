package com.bit.academy.controller;

import com.bit.academy.model.BoardVO;
import com.bit.academy.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;


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


    @GetMapping("/board/view/{qna_no}")
    public String boardView(Model model, @PathVariable int qna_no){
        model.addAttribute("board", this.boardService.selectBoard(qna_no));
        return "board/view";
    }

    @PostMapping("/board/delete/{qna_no}")
    public String boardDelete(HttpServletRequest request, @PathVariable int qna_no){
        this.boardService.deleteBoard(request, qna_no);
        return "board/delete_after";
    }

    @GetMapping("/board/update/{qna_no}")
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
    public String boardList(Model model){
        model.addAttribute("boardList", this.boardService.selectBoardList());

        List<BoardVO> boardList = new ArrayList<>();

        for(BoardVO boardVO:boardList){

        }

        return "board/list";
    }
}
