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
import java.util.HashMap;
import java.util.Map;


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
    public String Order(@ModelAttribute OrderDataVO orderDataVO, @ModelAttribute OrderinfoVO orderinfoVO, Model model){
        log.debug("---------------------------------------");
        log.debug(orderDataVO.getOrderDataVOList().toString());
        log.debug(orderinfoVO.toString());
        this.orderSerivce.insertOrder(orderDataVO.getOrderDataVOList(),orderinfoVO);

        model.addAttribute("orderDataList", this.orderSerivce.selectOrderData(orderinfoVO.getM_no()));
        return "payment/test";
    }

    @GetMapping("/order/test")
    public String test(Model model){
        Map<String, Object> map = new HashMap<String, Object>();
        map = this.orderSerivce.selectOrderData(3);
        model.addAttribute("orderDataList", this.orderSerivce.selectOrderData(3));

        return "payment/test";

    }

}
