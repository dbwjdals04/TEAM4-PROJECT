package com.bit.academy.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderDataVO {
    private int po_id;
    private int od_amount;
    private int od_price;
    private List<OrderDataVO> orderDataVOList;
}
