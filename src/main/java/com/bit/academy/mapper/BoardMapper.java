package com.bit.academy.mapper;

import com.bit.academy.model.BoardPaging;
import com.bit.academy.model.BoardVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BoardMapper {

    /**
     * 게시물 등록
     * @param boardVO
     */
    void insertBoard(BoardVO boardVO);

    /**
     * 답변 등록
     * @param boardVO
     */
    void replyBoard(BoardVO boardVO);

    /**
     * 게시물 수정
     * @param boardVO
     */
    void updateBoard(BoardVO boardVO);

    /**
     * 게시물 삭제
     * @param qna_no
     */
    void deleteBoard(int qna_no);

    /**
     * 게시물 조회
     * @param qna_no
     * @return
     */
    BoardVO selectBoard(int qna_no);

    /**
     * 게시물 목록 조회
     * @return
     */
    List<BoardVO> selectBoardList(BoardPaging boardPaging);

    /**
     * 게시물 갯수 조회
     * @return
     */
    int selectBoardListCount(BoardPaging boardPaging);

    /**
     * 비밀번호 체크
     * @return
     */
    int passck(BoardVO boardVO);


}