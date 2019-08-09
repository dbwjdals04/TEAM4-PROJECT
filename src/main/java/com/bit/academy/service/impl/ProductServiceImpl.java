package com.bit.academy.service.impl;

import com.bit.academy.mapper.ProductMapper;
import com.bit.academy.model.ProductVO;
import com.bit.academy.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    //상품검색(카테고리)
    @Override
    public ProductVO productSearchAll(List list) {
        return this.productMapper.productSearchAll(list);
    }
    //상품검색(세분)
    @Override
    public ProductVO productSearch(Integer c_no) {
        return this.productMapper.productSearch(c_no);
    }
    //상품등록
    @Override
    public void insertProduct(ProductVO productVO) {
        this.productMapper.insertProduct(productVO);
    }
    //상품상세페이지조회
    @Override
    public Map<String, Object> productDetail(Integer p_id) {
        return this.productMapper.productDetail(p_id);
    }
}
