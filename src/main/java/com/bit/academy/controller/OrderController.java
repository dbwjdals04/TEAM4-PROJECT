package com.bit.academy.controller;

import com.bit.academy.model.OrderDataVO;
import com.bit.academy.model.OrderVO;
import com.bit.academy.model.OrderinfoVO;
import com.bit.academy.service.OrderSerivce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
@Slf4j
public class OrderController {

    @Autowired
    private OrderSerivce orderSerivce;

    @GetMapping("/mypage/order")
    public String order(){
        return "/mypage/order";
    }

    @PostMapping("/admin/orderList")
    public String adminOrderList(){
        return "admin/OrderList";
    }

    @PostMapping("/order")
    public String test(@ModelAttribute OrderDataVO orderDataVO, @ModelAttribute OrderinfoVO orderinfoVO, HttpServletRequest request
            , Model model){
        log.debug("---------------------------------------");
        log.debug(orderDataVO.getOrderDataVOList().toString());
        log.debug(orderinfoVO.toString());
        this.orderSerivce.insertOrder(orderDataVO.getOrderDataVOList(),orderinfoVO);

        this.orderSerivce.selectOrderData(orderinfoVO.getM_no());

        return "payment/test";
    }

}
