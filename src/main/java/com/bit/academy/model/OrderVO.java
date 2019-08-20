package com.bit.academy.model;

import lombok.Data;

@Data
public class OrderVO {

    private int o_no;
    private int m_no;
    private int o_price;
    private String o_address;
    private String o_number;
    private int o_cancel;
    private String o_date;
    private int payment;

    private int od_no;
    private int po_id;
    private int o_amount;

    public int getO_no() {
        return o_no;
    }

    public void setO_no(int o_no) {
        this.o_no = o_no;
    }

    public int getM_no() {
        return m_no;
    }

    public void setM_no(int m_no) {
        this.m_no = m_no;
    }

    public int getO_price() {
        return o_price;
    }

    public void setO_price(int o_price) {
        this.o_price = o_price;
    }

    public String getO_address() {
        return o_address;
    }

    public void setO_address(String o_address) {
        this.o_address = o_address;
    }

    public String getO_number() {
        return o_number;
    }

    public void setO_number(String o_number) {
        this.o_number = o_number;
    }

    public int getO_cancel() {
        return o_cancel;
    }

    public void setO_cancel(int o_cancel) {
        this.o_cancel = o_cancel;
    }

    public String getO_date() {
        return o_date;
    }

    public void setO_date(String o_date) {
        this.o_date = o_date;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public int getOd_no() {
        return od_no;
    }

    public void setOd_no(int od_no) {
        this.od_no = od_no;
    }

    public int getPo_id() {
        return po_id;
    }

    public void setPo_id(int po_id) {
        this.po_id = po_id;
    }

    public int getO_amount() {
        return o_amount;
    }

    public void setO_amount(int o_amount) {
        this.o_amount = o_amount;
    }
}
