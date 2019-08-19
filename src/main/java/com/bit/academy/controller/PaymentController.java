package com.bit.academy.controller;

import com.bit.academy.service.PaymentService;
import com.bit.academy.util.IamportUtil;
import com.siot.IamportRestClient.request.CancelData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.font.AttributeMap;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

   /* @PostMapping("/payment/cart/{m_no}")
    public String cartview(Model model, @PathVariable int m_no){
        model.addAttribute("cart", this.paymentService.cartview(m_no));
        //model.addAttribute("cartsum", this.paymentService.amountsum(m_no));
        return "payment/cart";
    }
    @GetMapping("/payment/amountsum/{m_no}")
    public String amountsum(Model model, @PathVariable int m_no){
        model.addAttribute("cartsum", this.paymentService.amountsum(m_no));
        return "payment/amountsum";
    }*/
    @PostMapping("/payment/cart/{m_no}")
    public String cartview(Model model, @PathVariable int m_no){
       Map<String, Object> map = new HashMap<>();
       map= this.paymentService.cartview(m_no);
       model.addAttribute("map",map);

       return "payment/cart";
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

    @GetMapping("/payment/goCart")
    public String goCartGet(){
        return "/payment/cart";
    }
    @ResponseBody
    @PostMapping("/payment/goCart/")
    public Integer goCart(@RequestParam("cart[]") List cart){

        log.debug("장바구니요청!!!!!!!!!!!!!!");
        this.paymentService.goCart(cart);

        return 1;
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

    @PostMapping("/payment/cart/delete/{cart_no}")
    public String deleteCart(@PathVariable int cart_no){

        this.paymentService.deleteCart(cart_no);
        return "payment/cart";
    }

//    결제하기
    @GetMapping("/payment/buy/{p_id}/{m_no}/{po_id}/{cart_amount}")
    public String buyGet(Model model, HttpServletRequest request, @PathVariable int p_id, @PathVariable int m_no,
                         @PathVariable int po_id, @PathVariable int cart_amount ){
        log.debug("###############구매하기요청GETMAPPING#################");
        String result = "";
        log.debug(String.valueOf(request.getSession().getAttribute("member")));
        log.debug(String.valueOf(this.paymentService.buyMember(m_no)));
        Map<String, Object> map = new HashMap<>();

        if(this.paymentService.buyMember(m_no).equals(request.getSession().getAttribute("member"))){

            map.put("product",this.paymentService.buyProduct(p_id));
            map.put("member",this.paymentService.buyMember(m_no));
            map.put("option",this.paymentService.buyOption(po_id));
            map.put("cart_amount",cart_amount);

            model.addAttribute("map", map);
            result = "payment/payment2";
        }
        else{
            result = "error/errorpage";

        }
        return result;
    }
//
//    @ResponseBody
//    @PostMapping("/payment/buy/")
//    public Map<String, Object> buy(Model model, @RequestParam("p_id") int p_id, @RequestParam("m_no") int m_no,
//                      @RequestParam("po_id")int po_id, @RequestParam("cart_amount") int cart_amount){
//
//        log.debug("############구매하기요청##################");
//        Map<String, Object> map = new HashMap<>();
//
//        map.put("product", this.paymentService.buyProduct(p_id));
//        map.put("member", this.paymentService.buyMember(m_no));
//        map.put("option", this.paymentService.buyOption(po_id));
//        map.put("cart_amount",cart_amount);
//        log.debug(String.valueOf(map));
//
//        return map;
//    }




}
