package com.bit.academy.service;

import com.bit.academy.model.OrderDataVO;
import com.bit.academy.model.OrderinfoVO;

import java.util.List;
import java.util.Map;

public interface OrderSerivce {
    void insertOrder(List<OrderDataVO> list, OrderinfoVO orderinfoVO);
    Map<String, Object> selectOrderData(int m_no);
}
