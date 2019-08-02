package com.bit.academy.service;

import com.bit.academy.model.MemberVO;

import javax.servlet.http.HttpServletRequest;

public interface MemberService {

    MemberVO login(MemberVO memberVO, HttpServletRequest request);
}
