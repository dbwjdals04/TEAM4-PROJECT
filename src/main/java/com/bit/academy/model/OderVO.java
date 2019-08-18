package com.bit.academy.model;

import lombok.Data;

@Data
public class OderVO{

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
}
