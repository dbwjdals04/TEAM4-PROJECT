package com.bit.academy.mapper;

import com.bit.academy.model.BoardPaging;
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
    //admin - 전체 주문 보기
    List<OrderinfoVO> selectAllOrderData(BoardPaging boardPaging);
    int selectAllOrderCount(BoardPaging boardPaging);

    //admin - 주문 조회
   List<Map<String, Object>> selectOrderProduct(int o_no);
   List<Map<String, Object>> selectOrderData(int o_no);


    void insertOrderData(OrderDataVO orderDataVO);
    void insertOrderInfo(OrderinfoVO orderinfoVO);
//    List<OrderDataVO> selectOrderData(int m_no);
    OrderinfoVO selectOrderInfo(int m_no);
    List<Map<String, Object>> selectProductData(int m_no);
    void updateStock(int po_id, int od_amount);
}
