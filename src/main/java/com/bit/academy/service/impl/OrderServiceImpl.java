package com.bit.academy.service.impl;

import com.bit.academy.mapper.MemberMapper;
import com.bit.academy.mapper.OrderMapper;
import com.bit.academy.model.MemberVO;
import com.bit.academy.model.OrderAfterVO;
import com.bit.academy.model.OrderDataVO;
import com.bit.academy.model.OrderinfoVO;
import com.bit.academy.service.MemberService;
import com.bit.academy.service.OrderSerivce;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@Service
public class OrderServiceImpl implements OrderSerivce {

    @Autowired
    private OrderMapper orderMapper;

    //admin 주문 관리 페이지 출력
    @Override
    public List<OrderinfoVO> selectAllOrderData() {
        return this.orderMapper.selectAllOrderData();
    }

    @Override
    public void insertOrder(List<OrderDataVO> list, OrderinfoVO orderinfoVO) {
        this.orderMapper.insertOrderInfo(orderinfoVO);
        for(OrderDataVO orderDataVO : list){
            this.orderMapper.insertOrderData(orderDataVO);
            log.debug("############po_id : " + orderDataVO.getPo_id());
            log.debug("############od_amount : " + orderDataVO.getOd_amount());
            this.orderMapper.updateStock(orderDataVO.getPo_id(), orderDataVO.getOd_amount());
        }


    }

    @Override
    public Map<String,Object> selectOrderData(int m_no) {
        Map<String, Object> result = new HashMap<String, Object>();
        OrderinfoVO orderInfo = this.orderMapper.selectOrderInfo(m_no);
//        List<OrderDataVO> orderDataVOList = this.orderMapper.selectOrderData(m_no);
        List<Map<String, Object>> productDataList = this.orderMapper.selectProductData(orderInfo.getO_no());
//        for (OrderDataVO orderDataVO : orderDataVOList){
//            productDataList.add(this.orderMapper.selectProductData(orderDataVO.getPo_id()));
//        }

//        result.put("orderData", orderDataVOList);
        result.put("orderInfo", orderInfo);
        result.put("productData", productDataList);

        log.debug(result.toString());

        return result;

    }
}