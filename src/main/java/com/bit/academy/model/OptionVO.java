package com.bit.academy.model;

import lombok.Data;

import java.util.List;

@Data
public class OptionVO extends ProductVO {
    private int po_id;
    private int p_id;
    private String po_value;
    private int po_price;
    private int po_stock;
    private List<OptionVO> OptionVOList;
}
