package com.bit.academy.mapper;

import com.bit.academy.model.OrderAfterVO;
import com.bit.academy.model.OrderDataVO;
import com.bit.academy.model.OrderinfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface OrderMapper {

    Map<String, Object> selectAllOrderData();
    void insertOrderData(OrderDataVO orderDataVO);
    void insertOrderInfo(OrderinfoVO orderinfoVO);
//    List<OrderAfterVO> selectOrderData(int m_no);
    List<OrderDataVO> selectOrderData(int m_no);
    OrderinfoVO selectOrderInfo(int m_no);
    List<Map<String, Object>> selectProductData(int m_no);
}
