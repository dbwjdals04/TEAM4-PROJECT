package com.bit.academy.service;

import com.bit.academy.model.BoardPaging;
import com.bit.academy.model.OrderAfterVO;
import com.bit.academy.model.OrderDataVO;
import com.bit.academy.model.OrderinfoVO;

import java.util.List;
import java.util.Map;

public interface OrderSerivce {
    Map<String,Object> selectAllOrderData(BoardPaging boardPaging);
    Map<String,Object> selectOrder(int o_no);
    void insertOrder(List<OrderDataVO> list, OrderinfoVO orderinfoVO);
    Map<String, Object> selectOrderData(int m_no);
}
