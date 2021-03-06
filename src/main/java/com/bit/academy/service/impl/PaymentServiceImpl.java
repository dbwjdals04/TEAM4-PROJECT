package com.bit.academy.service.impl;

import com.bit.academy.mapper.PaymentMapper;
import com.bit.academy.model.*;
import com.bit.academy.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentMapper paymentMapper;

    /*@Override
    public List<CartVO> cartview(int m_no){
        List<CartVO> result = this.paymentMapper.cartview(m_no);
        return result;
    }
    @Override
    public Integer amountsum(int m_no){
        int result=this.paymentMapper.amountsum(m_no);
        return result;
    }*/
    @Override
    public Map<String, Object> cartview(int m_no){

        Map<String, Object> map = new HashMap<>();
        map.put("cart", this.paymentMapper.cartview(m_no));
        map.put("amount", this.paymentMapper.amountsum(m_no));
        map.put("total", this.paymentMapper.totalpay(m_no));
        return map;

    }
    /*@Override
    public CartVO cartview(int m_no, HttpServletRequest request) {
        log.debug("############ 세션 요청 정보 #############");
        log.debug(String.valueOf(request.getSession().getAttribute("member.m_no")));

       // if(request.getSession().getAttribute("member.m_no")==)
        return this.paymentMapper.cartview(m_no);}*/

    //장바구니에 담기
    @Override
    public void goCart(List list) {
        this.paymentMapper.goCart(list);
    }

    @Override
    public int cartOverlap(int po_id, int m_no) {
        int result = 3;
        log.debug(String.valueOf(this.paymentMapper.cartOverlap(po_id,m_no)));
        if(this.paymentMapper.cartOverlap(po_id,m_no)==null){
                result =1;
        }
        else if(this.paymentMapper.cartOverlap(po_id,m_no)!=null){
                result =0;
        }
        log.debug("Service:" + result);
        return result;
    }

    //장바구니 삭제
    @Override
    public  void deleteCart(int cart_no){ this.paymentMapper.deleteCart(cart_no);}


    //구매하기
    @Override
    public ProductVO buyProduct(int p_id) {

        return this.paymentMapper.buyProduct(p_id);
    }
    @Override
    public MemberVO buyMember(int m_no) {
        return this.paymentMapper.buyMember(m_no);
    }
    @Override
    public OptionVO buyOption(int po_id) {

        return this.paymentMapper.buyOption(po_id);
    }

    //장바구니에서 구매하기
    @Override
    public CartVO cartBuy(int cart_no) {
        return this.paymentMapper.cartBuy(cart_no);
    }
    //총합계
    @Override
    public int sumpay(List list) {
        return this.paymentMapper.sumpay(list);
    }

    //카트에서 변경한 값 실시간 반영(수량)
    @Override
    public void cartAmount(int cart_no, int cart_amount) {
        this.paymentMapper.cartAmount(cart_no, cart_amount);
    }

    //구매후 오더데이터 생성
    @Override
    public OrderVO orderData(OrderVO orderVO) {
        return this.paymentMapper.orderData(orderVO);
    }
}
