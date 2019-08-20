package com.bit.academy.service;

import com.bit.academy.model.BoardPaging;
import com.bit.academy.model.OptionVO;
import com.bit.academy.model.ProductVO;

import java.util.List;
import java.util.Map;

public interface ProductService {

    /**
     * 상품검색(카테고리)
     */
    List<ProductVO> productSearchAll(List list);

    Map<String, Object> selectProductList(List c_list, int currentPage);


    /**
     * 상품검색(세부)
     */
    List<ProductVO> productSearch(Integer c_no);

    /**
     *
     * 상품추가
     */
    void insertProduct(ProductVO productVO, Integer category_no, List<OptionVO> list);
    //test //void insertTest(List<OptionVO> list);

    /**
     * 상품상세페이지조회
     */
    Map<String, Object> productDetail(Integer p_id);
    /**
     * 상품조회
     */
    Map<String, Object> selectProduct(Integer p_id);

    /**
     * 상품수정
     */
    Map<String, Object> updateProduct(ProductVO productVO, int category_no, List<OptionVO> list);

}
