package com.bit.academy.controller;

import com.bit.academy.model.BoardPaging;
import com.bit.academy.model.CartVO;
import com.bit.academy.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment/cart/{m_no}")
    public String cartview(Model model, @PathVariable int m_no, HttpServletRequest request){


        model.addAttribute("cart", this.paymentService.cartview(m_no,request));
        return "payment/cart";
    }
    @PostMapping("/payment/cart")
    public String index(){
        return "payment/cart";
    }
}
