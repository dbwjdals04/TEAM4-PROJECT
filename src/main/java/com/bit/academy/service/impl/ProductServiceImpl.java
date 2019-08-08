package com.bit.academy.service.impl;

import com.bit.academy.mapper.ProductMapper;
import com.bit.academy.model.ProductVO;
import com.bit.academy.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductVO productSearchAll(List list) {

        return this.productMapper.productSearchAll(list);

    }

    @Override
    public ProductVO productSearch(Integer c_no) {
        return this.productMapper.productSearch(c_no);
    }

    @Override
    public void insertProduct(ProductVO productVO) {

        this.productMapper.insertProduct(productVO);
    }

}
