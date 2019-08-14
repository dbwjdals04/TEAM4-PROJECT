package com.bit.academy.service.impl;

import com.bit.academy.mapper.PaymentMapper;
import com.bit.academy.model.CartVO;
import com.bit.academy.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public CartVO cartview(int m_no, HttpServletRequest request) {
        log.debug("############ 세션 요청 정보 #############");
        log.debug(String.valueOf(request.getSession().getAttribute("member.m_no")));

       // if(request.getSession().getAttribute("member.m_no")==)
        return this.paymentMapper.cartview(m_no);}

    //장바구니에 담기
    @Override
    public void goCart(List list) {
        this.paymentMapper.goCart(list);
    }

}
