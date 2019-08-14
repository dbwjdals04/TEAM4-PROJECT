package com.bit.academy.mapper;

import com.bit.academy.model.CartVO;
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
}
