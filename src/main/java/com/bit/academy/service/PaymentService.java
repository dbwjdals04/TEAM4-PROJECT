package com.bit.academy.service;

import com.bit.academy.model.CartVO;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface PaymentService {

    /**
     * 카트조회
     * @param m_no
     * @return
     */
    CartVO cartview(int m_no, HttpServletRequest request);
}
