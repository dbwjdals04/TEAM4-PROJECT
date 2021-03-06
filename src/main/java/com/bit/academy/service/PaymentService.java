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
    int cartOverlap(int po_id, int m_no);


    void deleteCart(int cart_no);

    /**
     * 구매하기
     */
    ProductVO buyProduct(int p_id);
    MemberVO buyMember(int m_no);
    OptionVO buyOption(int po_id);

    /**
     * 카트에서 변경된 수량 실시간 데이터베이스 반영
     */
    void cartAmount(int cart_no, int cart_amount);

    /**
     * 장바구니에서 구매하기
     */
    CartVO cartBuy(int cart_no);

    int sumpay(List list);
    /**
     * 구매후 오더데이터 생성
     */
    OrderVO orderData(OrderVO orderVO);
}
