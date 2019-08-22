package com.bit.academy.model;

import lombok.Data;

import java.util.Date;

@Data
public class OrderAfterVO {
    private int o_no; // 전체 주문 번호
    private int po_id; // product option 고유번호 //
    private String od_amount; // 개별 주문 개수 //
    private int od_price; // 개별 주문 가격 //
    private String o_name;  // 주문자 성명
    private int o_price; // 전체 주문 가격
    private String o_postcode; // 주문자 우편번호
    private String o_roadAddress; // 주문자 도로명 주소
    private String o_detailAddress; // 주문자 상세 주소
    private String o_number; // 주문자 전화번호
    private int o_cancel; // 취소 여부
    private Date o_date;  // 주문 날짜
//    private String p_name;
//    private String p_price;
//    private String op_name;
//    private String price_amount;
//    private String orderMethod;

}
