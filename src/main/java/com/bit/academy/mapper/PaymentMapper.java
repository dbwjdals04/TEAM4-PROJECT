package com.bit.academy.mapper;

import com.bit.academy.model.CartVO;
import com.bit.academy.model.MemberVO;
import com.bit.academy.model.OptionVO;
import com.bit.academy.model.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PaymentMapper {
    /**
     * 카트조회
     * @param m_no
     * @return
     */
    List<CartVO> cartview(int m_no);
    //CartVO cartview(int m_no);
   /**
     * 카트수
     * @param m_no
     * @return
     */
   Integer amountsum(int m_no);
    /**
     * 총금액
     * @param m_no
     * @return
     */
   Integer totalpay(int m_no);

    //장바구니담기
    void goCart(List list);

    //장바구니 삭제
    void deleteCart(int cart_no);

    //구매하기
    ProductVO buyProduct(int p_id);
    MemberVO buyMember(int m_no);
    OptionVO buyOption(int po_id);

    //장바구니 구매하기
    CartVO cartBuy(int cart_no);
}
