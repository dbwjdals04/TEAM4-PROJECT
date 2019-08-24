package com.bit.academy.model;

import lombok.Data;

import java.util.Date;

@Data
public class OrderAfterVO {

    private String o_date;
    private int o_no;
    private String thumb_100;
    private String p_name;
    private String po_value;
    private String m_name;
    private int od_amount;
    private int od_price;
    private int o_cancel;

    public String getO_data() {
        return o_date;
    }

    public void setO_data(String o_data) {
        this.o_date = o_data;
    }

    public String getThumb_100() {
        return thumb_100;
    }

    public void setThumb_100(String thumb_100) {
        this.thumb_100 = thumb_100;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getPo_value() {
        return po_value;
    }

    public void setPo_value(String po_value) {
        this.po_value = po_value;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public int getOd_amount() {
        return od_amount;
    }

    public void setOd_amount(int od_amount) {
        this.od_amount = od_amount;
    }

    public int getOd_price() {
        return od_price;
    }

    public void setOd_price(int od_price) {
        this.od_price = od_price;
    }

    public int getO_cancel() {
        return o_cancel;
    }

    public void setO_cancel(int o_cancel) {
        this.o_cancel = o_cancel;
    }
}
