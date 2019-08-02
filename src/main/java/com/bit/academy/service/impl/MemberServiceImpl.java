package com.bit.academy.service.impl;

import com.bit.academy.model.MemberVO;
import com.bit.academy.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    public MemberVO login(MemberVO memberVO, HttpServletRequest request){

        log.debug("############ 로그인 요청 정보 #############");

        log.debug(memberVO.toString());

        /**
         * Mapper 통해 Database 에서 조회한 회원정보
         */
        memberVO.setM_address("서울");
        memberVO.setM_birth("10월");
        memberVO.setM_no(1);
        memberVO.setM_phone("010-1234-5678");
        memberVO.setM_name("홍길동");

        if(memberVO!=null){
            request.getSession().setAttribute("member", memberVO);
        }
        else{
            // log out 도 동일하게 처리 가능합니다.
            request.getSession().removeAttribute("member");
        }

        return memberVO;
    }
}
