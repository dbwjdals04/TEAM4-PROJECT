package com.bit.academy.mapper;

import com.bit.academy.model.BoardPaging;
import com.bit.academy.model.CartVO;
import com.bit.academy.model.OptionVO;
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
    List<ProductVO> productSearchAll(List c_no);

    /**
     * 상품메인화면 기본
     *
     */
    List<ProductVO> productMain();


    /**
     * 상품 갯수 조회
     * @return
     */
    int selectProductListCount(List c_noList);

    /**
     * 상품 목록 조회 - admin
     * @return
     */
    List<ProductVO> selectProductList(Map<String, Object> map);

    /**
     * 상품검색기능(세부)
     * @param c_no
     */
    List<ProductVO> productSearch(Integer c_no);

    /**
     * 상품추가기능
     * @param productVO
     */
    void insertProduct(ProductVO productVO);
    void insertCategory(Integer category_no);
    void insertOption(OptionVO optionVO);

    /**
     * 상품상세페이지
     * @Param p_id
     * @return
     */
    ProductVO productDetail(Integer p_id);
    List<OptionVO> productOption(Integer p_id);
    List<CartVO> productAmount(Integer p_id);

    /**
     * 상품 조회
     * @Param p_id
     * @return
     */
    Map<String, Object> selectProduct(Integer p_id);
    List<OptionVO> selectOption(Integer p_id);

    /**
     * 상품 수정
     * @Param p_id
     * @return
     */
    void updateProduct(Map map);
    void deleteOption(int p_id);
    void insertOption2(OptionVO optionVO);
}
