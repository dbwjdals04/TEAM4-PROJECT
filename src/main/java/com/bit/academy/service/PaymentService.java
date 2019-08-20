package com.bit.academy.service;

import com.bit.academy.model.*;

import java.util.List;
import java.util.Map;

public interface PaymentService {

    /*/**
     * 카트조회
     * @param m_no
     * @return
     */
   // List<CartVO> cartview(int m_no);
    //CartVO cartview(int m_no, HttpServletRequest request);
   /* /**
     * 카트수
     * @param m_no
     * @return
     */
  // Integer amountsum(int m_no);
    Map<String, Object> cartview(int m_no);

    /**
     * 카트담기
     * @param list
     */
    void goCart(List list);

    void deleteCart(int cart_no);

    /**
     * 구매하기
     */
    ProductVO buyProduct(int p_id);
    MemberVO buyMember(int m_no);
    OptionVO buyOption(int po_id);
    /**
     * 장바구니에서 구매하기
     */
    CartVO cartBuy(int cart_no);
    /**
     * 구매후 오더데이터 생성
     */
    OrderVO orderData(OrderVO orderVO);
}
