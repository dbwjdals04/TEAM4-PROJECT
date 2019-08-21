package com.bit.academy.controller;

import com.bit.academy.model.CartVO;
import com.bit.academy.model.OrderVO;
import com.bit.academy.service.OrderSerivce;
import com.bit.academy.service.PaymentService;
import com.bit.academy.util.IamportUtil;
import com.siot.IamportRestClient.request.CancelData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;


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
    public String paymentExecute(@ModelAttribute OrderVO orderVO, Model model, HttpServletRequest request){

        //오더데이터생성 및 다음페이지 전달
        log.debug(String.valueOf(this.paymentService.orderData(orderVO)));
        model.addAttribute("order",this.paymentService.orderData(orderVO));
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

        return "payment/orderAfter";
    }

    @PostMapping("/payment/cart/delete/{cart_no}")
    public String deleteCart(@PathVariable int cart_no){

        this.paymentService.deleteCart(cart_no);
        return "payment/cart";
    }

    //장바구니 선택 갯수에 따라 실시간으로 달라지는 데이터베이스
    @ResponseBody
    @PostMapping("/payment/cartAmount")
    public int cartAmount(@RequestParam("cart_number[]")List cart_number,
                              @RequestParam("cart_amount[]")List cart_amount){

        Integer[] cart_number_arr = new Integer[cart_number.size()];
        Integer[] cart_amount_arr = new Integer[cart_amount.size()];
        for(int i=0; i<cart_number.size(); i++){
            cart_number_arr[i] = Integer.parseInt((String) cart_number.get(i));
            cart_amount_arr[i] = Integer.parseInt((String) cart_amount.get(i));
            this.paymentService.cartAmount(cart_number_arr[i],cart_amount_arr[i]);
            log.debug("업데이트 완료"+(i+1)+"회");
         }

        return 1;
    }


    //결제하기
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

            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");//dd/MM/yyyy
            model.addAttribute("merchant_uid", sdfDate.format(new Date()).toString());

            model.addAttribute("map", map);
            result = "payment/payment2";
        }
        else{
            result = "error/errorpage";

        }
        return result;
    }
    //장바구니에서 결제하기
    @GetMapping("/payment/cartBuy/")
    public String cartBuyGet(){
        return "payment/payment3";
    }

    @PostMapping("/payment/cartBuy/")
    public String cartBuy(HttpServletRequest request
                        ,Model model){
        log.debug("############결제요청#############");

        String[] cart_no_arr_str = request.getParameterValues("cart_no_arr");
        Integer[] cart_no_arr = new Integer[cart_no_arr_str.length];
        List<Object> list = new ArrayList<>();
        for(int i=0;i<cart_no_arr_str.length;i++){
            cart_no_arr[i] = Integer.parseInt(cart_no_arr_str[i]);
            log.debug("cart_no : " + cart_no_arr[i]);
            list.add(this.paymentService.cartBuy(cart_no_arr[i]));
            log.debug(String.valueOf(list));
        }


        log.debug(String.valueOf(list));
        log.debug("#########################");
        log.debug(String.valueOf(this.paymentService.sumpay(Arrays.asList(cart_no_arr))));
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");//dd/MM/yyyy
        model.addAttribute("sumpay", this.paymentService.sumpay(Arrays.asList(cart_no_arr)));
        model.addAttribute("merchant_uid", sdfDate.format(new Date()).toString());
        model.addAttribute("list", list);

        return "payment/payment3";
    }
//    @RequestParam("total") int total
//            log.debug(String.valueOf(total));



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
