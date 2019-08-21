package com.bit.academy.model;

import lombok.Data;

@Data
public class OrderAfterVO {
    private String m_name;
    private String m_id;
    private String m_phone;
    private int postcode;
    private String address;
    private String addressDetail;
    private String p_name;
    private String p_price;
    private String amount;
    private String op_name;
    private String op_price;
    private String price_amount;
    private String orderData;
    private String orderMethod;
}
