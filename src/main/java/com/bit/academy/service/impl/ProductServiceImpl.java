package com.bit.academy.service.impl;

import com.bit.academy.mapper.ProductMapper;
import com.bit.academy.model.ProductVO;
import com.bit.academy.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    //상품검색(카테고리)
    @Override
    public List<ProductVO> productSearchAll(List list) {
        List<ProductVO> result = this.productMapper.productSearchAll(list);
        return result;
    }
    //상품검색(세분)
    @Override
    public List<ProductVO> productSearch(Integer c_no) {
        List<ProductVO> list = this.productMapper.productSearch(c_no);
        return list;
    }
    //상품등록
    @Override
    public void insertProduct(ProductVO productVO, Integer category_no) {
        this.productMapper.insertProduct(productVO);
        this.productMapper.insertCategory(category_no);

    }
    //상품상세페이지조회
    @Override
    public Map<String, Object> productDetail(Integer p_id) {
        return this.productMapper.productDetail(p_id);
    }
}
