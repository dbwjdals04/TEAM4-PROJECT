package com.bit.academy.service.impl;

import com.bit.academy.mapper.PaymentMapper;
import com.bit.academy.model.CartVO;
import com.bit.academy.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentMapper paymentMapper;


    @Override
    public CartVO cartview(int m_no) {
        return this.paymentMapper.cartview(m_no);
    }
}
