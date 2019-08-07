package com.bit.academy.mapper;

import com.bit.academy.model.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MemberMapper {

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
    List<MemberVO> selectMemberList();


    List<MemberVO> searchMemberList(String searchOption, String searchKeyword);

    void updateMember(MemberVO memberVO);

    /**
     * 회원가입
     * @param memberVO
     */
    void regist(MemberVO memberVO);

    /**
     * 로그인
     * @return
     * @param memberVO
     */
    MemberVO login(MemberVO memberVO);

    /**
     * 아이디찾기
     * @return
     * @param memberVO
     */
    MemberVO idFind(MemberVO memberVO);
    /**
     * 패스워드찾기
     * @return
     * @param memberVO
     */
    MemberVO pwFind(MemberVO memberVO);


}
