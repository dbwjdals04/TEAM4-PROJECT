package com.bit.academy.service.impl;

import com.bit.academy.mapper.MemberMapper;
import com.bit.academy.model.BoardPaging;
import com.bit.academy.model.MemberVO;
import com.bit.academy.model.OrderAfterVO;
import com.bit.academy.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {


    //휘연이꺼
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public MemberVO selectMember(int m_no) {
        return this.memberMapper.selectMember(m_no);
    }

    @Override
    public Map<String, Object> selectMemberList(BoardPaging boardPaging) {

        Map<String,Object> map = new HashMap<>();

        /**
         * 처음 진입한 경우 currentPage 등 초기화 해줍니다.
         */
        if(boardPaging.getCurrentPage()==0){
            boardPaging.setCurrentPage(1); // 1page 부터 조회
            boardPaging.setArticleCount(10); // 페이지당 게시물 갯수
        }

        boardPaging.setTotalCount(this.memberMapper.selectMemberListCount(boardPaging, null, null));

        map.put("boardPaging", boardPaging);
        map.put("memberList", this.memberMapper.selectMemberList(boardPaging));

        return map;
    }

    @Override
    public Map<String,Object> searchMemberList(String searchOption, String searchKeyword, BoardPaging boardPaging) {

        log.debug("실행!");
//
        Map<String,Object> map = new HashMap<>();
//
//        /**
//         * 처음 진입한 경우 currentPage 등 초기화 해줍니다.
//         */
        if(boardPaging.getCurrentPage()==0){
            boardPaging.setCurrentPage(1); // 1page 부터 조회
            boardPaging.setArticleCount(10); // 페이지당 게시물 갯수
        }

        int i = this.memberMapper.selectMemberListCount(boardPaging, searchOption, searchKeyword);

        boardPaging.setTotalCount(i);
        log.debug(String.valueOf(boardPaging.getTotalCount()));
//        boardPaging.setTotalCount(5);
//        log.debug(boardPaging.toString());
//
        map.put("boardPaging", boardPaging);
        map.put("memberList", this.memberMapper.searchMemberList(searchOption, searchKeyword, boardPaging));
        return map;
//        return this.memberMapper.searchMemberList(searchOption, searchKeyword, boardPaging);
    }

    @Override
    public MemberVO memberInfo(MemberVO memberVO, String chk_pw) {
        log.debug("#########################");
        log.debug(memberVO.toString());
        int m_no = memberVO.getM_no();
        memberVO = this.memberMapper.selectMember(memberVO.getM_no());
        //MemberVO memberVO1 = this.memberMapper.selectMember(memberVO.getM_no());

        /*memberVO.setM_address("testadd");
        memberVO.setM_id("test1id");
        memberVO.setM_no(2);
        memberVO.setM_phone("010-1234-5678");
        memberVO.setM_name("test1");
        memberVO.setM_password("test1pw");
        memberVO.setM_regdate("2019-08-01 01:31:12");*/
        log.debug(memberVO.toString());


        if (memberVO.getM_password().equals(chk_pw)) {
            //request.getSession().setAttribute("member", memberVO);
            log.debug("success");
            return memberVO;
        } else {
            log.debug("fail");
            return null;
        }
    }

    @Override
    public MemberVO memberUpdate(MemberVO memberVO) {

        this.memberMapper.updateMember(memberVO);

        return null;
    }

    @Override
    public void regist(MemberVO memberVO) {
        this.memberMapper.regist(memberVO);
    }

    //로그인
    @Override
    public MemberVO login(MemberVO memberVO, HttpServletRequest request) {


        log.debug("############ 로그인 요청 정보 #############");

        log.debug(memberVO.toString());


        MemberVO DBmemberVO = (MemberVO) this.memberMapper.login(memberVO);


        if (DBmemberVO != null) {
            request.getSession().setAttribute("member", DBmemberVO);
            request.getSession().setMaxInactiveInterval(60 * 30);

            log.debug(String.valueOf(request.getSession().getAttribute("member")));

        } else if (DBmemberVO == null) {
            request.getSession(false);
//            String id = (String)request.getSession().getAttribute("member");
//            log.debug(id);
            // log out 도 동일하게 처리 가능합니다.
//            request.getSession().removeAttribute("member");
        }

        return DBmemberVO;
    }

    //아이디찾기
    //아이디찾기

    @Override
    public MemberVO idFind(MemberVO memberVO) {
        MemberVO amemberVO = this.memberMapper.idFind(memberVO);

        return amemberVO;
    }

    //비번찾기

    @Override
    public MemberVO pwFind(MemberVO memberVO) {
        MemberVO amemberVO = this.memberMapper.pwFind(memberVO);
        return amemberVO;
    }

    @Override
    public void logOut(HttpServletRequest request) {
        request.getSession().removeAttribute("member");
    }

    @Override
    public void modify(MemberVO memberVO) {
        this.memberMapper.modifyMember(memberVO);
    }

    @Override
    public Map<String, Object> myOrderData(int m_no, BoardPaging boardPaging) {

        Map<String,Object> map = new HashMap<>();

        /**
         * 처음 진입한 경우 currentPage 등 초기화 해줍니다.
         */
        if(boardPaging.getCurrentPage()==0){
            boardPaging.setCurrentPage(1); // 1page 부터 조회
            boardPaging.setArticleCount(10); // 페이지당 게시물 갯수
        }

        boardPaging.setTotalCount(this.memberMapper.selectOrderListCount(m_no));

        map.put("boardPaging", boardPaging);
        map.put("myOrderData", this.memberMapper.myOrderData(m_no, boardPaging));

        return map;
//        return this.memberMapper.myOrderData(m_no, boardPaging);
    }
}