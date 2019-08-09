package com.bit.academy.controller;

import com.bit.academy.model.MemberVO;
import com.bit.academy.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/admin/member/{searchOption}/{searchKeyword}")
    public String searchMember(Model model, @PathVariable String searchOption, @PathVariable String searchKeyword){
        model.addAttribute("memberList", this.memberService.searchMemberList(searchOption, searchKeyword));
        List<MemberVO> memberList = new ArrayList<>();

        for(MemberVO memberVO:memberList){

        }
        return "admin/memberList";
    }

    @ResponseBody
    @GetMapping("/admin/member/{m_no}")
    public MemberVO memberJSON(Model model, @PathVariable int m_no){
        return this.memberService.selectMember(m_no);
    }


    @GetMapping("/admin/memberList")
    public String memberList(Model model){
        model.addAttribute("memberList", this.memberService.selectMemberList());
        List<MemberVO> memberList = new ArrayList<>();

        for(MemberVO memberVO:memberList){

        }

        return "admin/memberList";
    }


    @GetMapping("/member/member_chk")
    public String memberChk(){
        return "member/member_check";
    }


    @GetMapping("/member/member_info")
    public String memberInfo(){
        return "member/member_info";
    }

    @PostMapping("/member/member_info")
    public String testSession( @ModelAttribute MemberVO memberVO
            , Model model
            , HttpServletRequest request){

        String chk_pw = request.getParameter("chk_pw");

        model.addAttribute("member", this.memberService.memberInfo(memberVO, chk_pw));

        return "member/member_info";
    }

    @GetMapping("/member/member_update")
    public String memberUpdate(){
        return "member/member_info";
    }

    @PostMapping("/member/member_update")
    public String memberUpdateExecute(@ModelAttribute MemberVO memberVO, Model model, HttpServletRequest request){

        log.debug("===========================================");
        log.debug(memberVO.toString());
        this.memberService.memberUpdate(memberVO);


        model.addAttribute("member", this.memberService.selectMember(memberVO.getM_no()));

        return "member/member_info";
    }

    @GetMapping("/admin")
    public String index(){
        return "admin/adminMain";
    }

    // 회원가입 처리

    @GetMapping("/member/regist")
    public String regist() {

        return "member/regist";
    }

    @PostMapping("/member/regist")
    public String registExecute(Model model, @ModelAttribute MemberVO memberVO) {
        log.debug("---------회원가입 요청 처리-------");
        log.debug(memberVO.toString());

        log.debug("--------게시글 등록 결과---------");
        log.debug(memberVO.toString());

        memberVO = this.memberService.selectMember(memberVO.getM_no());
        model.addAttribute("member", memberVO);

        return "member/login";
    }

    //로그인

    /**
     * login form HTML
     * @return
     */
    @GetMapping("/member/login")
    public String login(MemberVO memberVO){

        return "member/login";
    }

    /**
     * login 수행
     * @param memberVO
     * @param model
     * @param request
     * @return
     */
    @PostMapping("/member/login")
    public String loginExecute(@ModelAttribute MemberVO memberVO, Model model, HttpServletRequest request){

        memberVO = this.memberService.login(memberVO, request);
        model.addAttribute("member", memberVO);
        return "member/main";
    }
    @GetMapping("/member/idPwFind")
    public String idPwFind(){
        return "member/idPwFind";
    }


    //아이디찾기
    @ResponseBody
    @GetMapping("/member/idFind")
    public MemberVO idFind(@RequestParam("m_name") String m_name,
                           @RequestParam("m_phone") String m_phone,
                           HttpServletResponse response,
                           MemberVO memberVO) {
        log.debug(m_name);
        log.debug(m_phone);
        memberVO.setM_name(m_name);
        memberVO.setM_phone(m_phone);
        MemberVO membervo = this.memberService.idFind(memberVO);
        log.debug(String.valueOf(membervo));
        return membervo;
    }
    //비밀번호찾기
    @ResponseBody
    @GetMapping("/member/pwFind")
    public MemberVO pwFind(@RequestParam("m_id") String m_id,
                           @RequestParam("m_phone") String m_phone,
                           HttpServletResponse response,
                           MemberVO memberVO){
        log.debug(m_id);
        log.debug(m_phone);
        memberVO.setM_id(m_id);
        memberVO.setM_phone(m_phone);
        log.debug(memberVO.toString());
        MemberVO membervo = this.memberService.pwFind(memberVO);

        return membervo;

    }

    //로그아웃
    @PostMapping("member/logOut")
    public String logOut(HttpServletRequest request){
        this.memberService.logOut(request);
        return "member/main";
    }
}