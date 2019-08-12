package com.bit.academy.mapper;

import com.bit.academy.model.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ProductMapper {


    /**
     * 상품검색기능
     * @param c_no
     */
    ProductVO productSearchAll(List c_no);

    /**
     * 상품검색기능(세부)
     * @param c_no
     */
    ProductVO productSearch(Integer c_no);

    /**
     * 상품추가기능
     * @param productVO
     */
    void insertProduct(ProductVO productVO);
    void insertCategory(Integer category_no);

    /**
     * 상품상세페이지
     * @Param p_no
     * @return
     */
    Map<String, Object> productDetail(Integer p_id);
}
