package com.bit.academy.service;

import com.bit.academy.model.BoardPaging;
import com.bit.academy.model.MemberVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface MemberService {

    /**
     * 상세 회원 정보
     * @param m_no
     * @return
     */
    MemberVO selectMember(int m_no);

    /**
     * 회원 리스트
     * @return
     */
    Map<String,Object> selectMemberList(BoardPaging boardPaging);

    Map<String,Object> searchMemberList(String searchOption, String searchKeyword, BoardPaging boardPaging);

    MemberVO memberInfo(MemberVO memberVO, String chk_pw);

    MemberVO memberUpdate(MemberVO memberVO);

    /**
     * 회원가입
     * @param memberVO
     */
    void regist(MemberVO memberVO);


    /**
     * 로그인
     * @return
     */


    MemberVO login(MemberVO memberVO, HttpServletRequest request);

    /**
     * idFInd
     * @return
     */


    MemberVO idFind(MemberVO memberVO);

    /**
     * pwFind
     * @return
     */


    MemberVO pwFind(MemberVO memberVO);

    /**
     * pwFind
     * @return
     */


    void logOut(HttpServletRequest request);

    void modify(MemberVO memberVO);
}
