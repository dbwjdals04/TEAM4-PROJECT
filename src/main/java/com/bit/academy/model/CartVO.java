package com.bit.academy.model;


import lombok.Data;


@Data
public class CartVO {
    private int cart_no;
    private int m_no;
    private int p_id;
    private int cart_amount;
    private int po_id;

    private String thumb_300;
    private String p_name;
    private int p_price;
}
