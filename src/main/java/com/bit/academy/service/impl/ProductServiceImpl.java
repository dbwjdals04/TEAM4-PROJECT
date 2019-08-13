package com.bit.academy.service.impl;

import com.bit.academy.mapper.ProductMapper;
import com.bit.academy.model.OptionVO;
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
        // this.productMapper.productList(list);
        return this.productMapper.productSearchAll(list);
    }
    //상품검색(세분)
    @Override
    public ProductVO productSearch(Integer c_no) {
        return this.productMapper.productSearch(c_no);
    }
    //상품등록
    @Override
    public void insertProduct(ProductVO productVO, Integer category_no, List<OptionVO> list) {
        this.productMapper.insertProduct(productVO);
        this.productMapper.insertCategory(category_no);
        for(OptionVO optionVO : list){
            this.productMapper.insertOption(optionVO);
        }


    }

    //test
//    @Override
//    public void insertTest(List<OptionVO> list) {
//        this.productMapper.insertOption(list);
//    }

    //상품상세페이지조회
    @Override
    public Map<String, Object> productDetail(Integer p_id) {
        log.debug(this.productMapper.productDetail(p_id).toString());
        Map<String, Object> map = new HashMap<String, Object>();
        //map.putthis.productMapper.productDetail(p_id);
        return map;
    }

    //상품 조회
    @Override
    public Map<String, Object> selectProduct(Integer p_id) {
        Map<String, Object> map = this.productMapper.selectProduct(p_id);
        map.put("option", this.productMapper.selectOption(p_id));
        return map;
    }
}
