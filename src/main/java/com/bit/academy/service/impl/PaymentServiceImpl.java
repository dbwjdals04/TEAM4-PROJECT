package com.bit.academy.service.impl;

import com.bit.academy.mapper.PaymentMapper;
import com.bit.academy.model.CartVO;
import com.bit.academy.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;

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

    @Override
    public Integer goCart(CartVO cartVO, HttpServletRequest request) {
        int result;
        if(request.getSession().getAttribute("member")!=null){
            cartVO.setM_no((Integer) request.getSession().getAttribute("m_no"));
            this.paymentMapper.goCart(cartVO);
            result = 1;
        }
        else{
            result = 0;
        }
        return result;
    }
}
