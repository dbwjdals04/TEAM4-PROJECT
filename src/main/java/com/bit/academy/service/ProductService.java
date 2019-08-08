package com.bit.academy.service;

import com.bit.academy.model.ProductVO;

import java.util.List;

public interface ProductService {

    /**
     * 상품검색(카테고리)
     */
    ProductVO productSearchAll(List list);

    /**
     * 상품검색(세부)
     */
    ProductVO productSearch(Integer c_no);

    /**
     *
     * 상품추가
     */
    void insertProduct(ProductVO productVO);

}
