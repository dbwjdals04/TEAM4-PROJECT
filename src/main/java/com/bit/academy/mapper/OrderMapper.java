package com.bit.academy.mapper;

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
    Map<String, Object> selectOrderData(int m_no);
}
