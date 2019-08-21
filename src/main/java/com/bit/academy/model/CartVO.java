package com.bit.academy.model;


import lombok.Data;

import java.util.List;


@Data
public class CartVO {
    private int cart_no;
    private int m_no;
    private String m_name;
    private String m_id;
    private String m_phone;
    private String m_address;
    private int p_id;
    private int cart_amount;
    private int po_id;

    private String thumb_100;
    private String thumb_300;
    private String p_name;
    private int p_price;

    private String po_value;
    private int po_price;
    private int po_stock;
    private List<OptionVO> OptionVOList;
    private int sumpay;




    public int getCart_no() {
        return cart_no;
    }

    public void setCart_no(int cart_no) {
        this.cart_no = cart_no;
    }

    public int getM_no() {
        return m_no;
    }

    public void setM_no(int m_no) {
        this.m_no = m_no;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public int getCart_amount() {
        return cart_amount;
    }

    public void setCart_amount(int cart_amount) {
        this.cart_amount = cart_amount;
    }

    public int getPo_id() {
        return po_id;
    }

    public void setPo_id(int po_id) {
        this.po_id = po_id;
    }

    public String getThumb_300() {
        return thumb_300;
    }

    public void setThumb_300(String thumb_300) {
        this.thumb_300 = thumb_300;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public int getP_price() {
        return p_price;
    }

    public void setP_price(int p_price) {
        this.p_price = p_price;
    }

    public int getSumpay() {
        return sumpay;
    }

    public void setSumpay(int sumpay) {
        this.sumpay = sumpay;
    }
}

