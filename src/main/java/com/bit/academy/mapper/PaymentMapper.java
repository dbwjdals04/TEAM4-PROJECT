package com.bit.academy.mapper;

import com.bit.academy.model.CartVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {
    /**
     * 카트조회
     * @param m_no
     * @return
     */
    CartVO cartview(int m_no);
}
