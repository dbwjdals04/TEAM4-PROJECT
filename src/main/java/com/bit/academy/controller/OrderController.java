package com.bit.academy.controller;

import com.bit.academy.model.OrderVO;
import com.bit.academy.service.OrderSerivce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@Slf4j
public class OrderController {

    @Autowired
    private OrderSerivce orderSerivce;

    @GetMapping("/mypage/order")
    public String order(){
        return "/mypage/order";
    }
}
