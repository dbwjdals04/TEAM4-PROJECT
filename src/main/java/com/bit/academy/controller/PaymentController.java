package com.bit.academy.controller;

import com.bit.academy.model.BoardPaging;
import com.bit.academy.model.CartVO;
import com.bit.academy.service.PaymentService;
import com.bit.academy.util.IamportUtil;
import com.siot.IamportRestClient.request.CancelData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;



@Slf4j
@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment/cart/{m_no}")
    public String cartview(Model model, @PathVariable int m_no){
        model.addAttribute("cart", this.paymentService.cartview(m_no));
        //model.addAttribute("cartsum", this.paymentService.amountsum(m_no));
        return "payment/cart";
    }
    @GetMapping("/payment/amountsum/{m_no}")
    public String amountsum(Model model, @PathVariable int m_no){
        model.addAttribute("cartsum", this.paymentService.amountsum(m_no));
        return "payment/amountsum";
    }
    //카트조회(로그인 후 자기 장바구니)
    /*@PostMapping("/payment/cart/{m_no}")
    public String cartview(Model model, @PathVariable int m_no, HttpServletRequest request){


        model.addAttribute("cart", this.paymentService.cartview(m_no,request));
        return "payment/cart";
    }*/
    //
    @PostMapping("/payment/cart")
    public String index(){
        return "payment/cart";
    }

    //카트에등록
    @PostMapping("/payment/goCart/")
    public String goCart(Model model, @ModelAttribute CartVO cartVO, HttpServletRequest request){

        model.addAttribute("result",this.paymentService.goCart(cartVO,request));
        return "payment/cart";
    }

    //결제
    @GetMapping("/payment")
    public String paymentForm(Model model){

        /**
         * 주문번호 생성
         */
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");//dd/MM/yyyy
        model.addAttribute("merchant_uid", sdfDate.format(new Date()).toString());

        return "payment/payment";
    }

    @PostMapping("/payment")
    public String paymentExecute(Model model, HttpServletRequest request){


        /**
         * 테스트 결제를 바로 취소 합니다.
         */
        String merchant_uid = request.getParameter("merchant_uid");
        IamportUtil iamportUtil = new IamportUtil();
        CancelData cancel_data = new CancelData(merchant_uid, false);

        try{
            iamportUtil.cancelPaymentByImpUid(cancel_data);
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return "payment/payment";
    }



}
