package com.bit.academy.service.impl;

import com.bit.academy.mapper.MemberMapper;
import com.bit.academy.mapper.OrderMapper;
import com.bit.academy.model.*;
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

    @Override
    public Map<String,Object> selectAllOrderData(BoardPaging boardPaging) {
        Map<String,Object> map = new HashMap<>();

        /**
         * 처음 진입한 경우 currentPage 등 초기화 해줍니다.
         */
        if(boardPaging.getCurrentPage()==0){
            boardPaging.setCurrentPage(1); // 1page 부터 조회
            boardPaging.setArticleCount(10); // 페이지당 게시물 갯수
        }

        boardPaging.setTotalCount(this.orderMapper.selectAllOrderCount(boardPaging));

        map.put("boardPaging", boardPaging);
        map.put("orderList", this.orderMapper.selectAllOrderData(boardPaging));

        return map;
    }

    @Override
    public Map<String, Object> selectOrder(int o_no) {
        Map<String, Object> map = new HashMap<>();

        map.put("product", this.orderMapper.selectOrderProduct(o_no));
        map.put("orderInfo", this.orderMapper.selectOrderData(o_no));

        log.debug(map.toString());

        return map;
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