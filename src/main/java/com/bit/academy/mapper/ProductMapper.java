package com.bit.academy.mapper;

import com.bit.academy.model.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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

}
