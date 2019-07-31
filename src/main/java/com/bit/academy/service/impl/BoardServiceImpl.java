package com.bit.academy.service.impl;

import com.bit.academy.mapper.BoardMapper;
import com.bit.academy.model.BoardVO;
import com.bit.academy.model.MemberVO;
import com.bit.academy.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public void insertBoard(BoardVO boardVO) {
        this.boardMapper.insertBoard(boardVO);
    }

    @Override
    public void updateBoard(BoardVO boardVO) {
        this.boardMapper.updateBoard(boardVO);
    }

    @Override
    public void deleteBoard(HttpServletRequest request, int qna_no) {

        /*
        // session을 통해 사용자 인증정보 조회
        MemberVO memberVO = (MemberVO)request.getSession().getAttribute("memberVO");

        // 해당 게시물 조회하여 등록된 사용자의 삭제 요청인지 비교
        BoardVO board = this.boardMapper.selectBoard(qna_no);
        if(board.getM_no() == memberVO.getM_no()){
            this.boardMapper.deleteBoard(qna_no);
        }
        */

        this.boardMapper.deleteBoard(qna_no);

    }

    @Override
    public BoardVO selectBoard(int qna_no) {
        return this.boardMapper.selectBoard(qna_no);
    }

    @Override
    public List<BoardVO> selectBoardList() {
        return this.boardMapper.selectBoardList();
    }
}
