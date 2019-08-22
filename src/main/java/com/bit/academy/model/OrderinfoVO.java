package com.bit.academy.model;

import lombok.Data;

import java.util.Date;

@Data
public class OrderinfoVO {
    private int o_no;
    private int m_no;
    private int o_price;
    private int o_cancel;
    private Date o_date;
    private String o_postcode;
    private String o_name;
    private String o_number;
    private String o_detailAddress;
    private String o_roadAddress;

}
