package com.bit.academy.service.impl;

import com.bit.academy.mapper.ProductMapper;
import com.bit.academy.model.BoardPaging;
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

        List<ProductVO> result = this.productMapper.productSearchAll(list);
        return result;

    }

    @Override
    public Map<String,Object> selectProductList(List list, int currentPage) {
        Map<String,Object> map = new HashMap<>();
        BoardPaging boardPaging = new BoardPaging();
        boardPaging.setCurrentPage(currentPage);

        /**
         * 처음 진입한 경우 currentPage 등 초기화 해줍니다.
         */
        if(boardPaging.getCurrentPage()==0){
            boardPaging.setCurrentPage(1); // 1page 부터 조회
            boardPaging.setArticleCount(10); // 페이지당 게시물 갯수
        }

        boardPaging.setTotalCount(this.productMapper.selectProductListCount(list));

        map.put("boardPaging", boardPaging);
        map.put("c_no", list);
        log.debug(map.toString());
        map.put("productList", this.productMapper.selectProductList(map));

        log.debug(map.toString());

        return map;
    }

    //상품검색(세분)
    @Override
    public List<ProductVO> productSearch(Integer c_no) {
        List<ProductVO> list = this.productMapper.productSearch(c_no);
        return list;
    }
    //상품등록
    @Override
    public void insertProduct(ProductVO productVO, Integer category_no, List<OptionVO> list) {
        this.productMapper.insertProduct(productVO);
        this.productMapper.insertCategory(category_no);
        log.debug("=============="+list.toString());
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
    public Map<String, Object> productDetail(Integer p_id){

        Map<String, Object> map = new HashMap<>();
        map.put("product", this.productMapper.productDetail(p_id));
        map.put("option", this.productMapper.productOption(p_id));
        map.put("amount", this.productMapper.productAmount(p_id));
        return map;

    }

    //상품 조회
    @Override
    public Map<String, Object> selectProduct(Integer p_id) {
        Map<String, Object> map = this.productMapper.selectProduct(p_id);
        map.put("option", this.productMapper.productOption(p_id));
        return map;
    }


    //상품 수정
    @Override
    public Map<String, Object> updateProduct(ProductVO productVO, int category_no, List<OptionVO> list) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("productVO", productVO);
        map.put("category_no", category_no);
        log.debug(map.toString());
        this.productMapper.updateProduct(map);
        this.productMapper.deleteOption(productVO.getP_id());
        for(OptionVO optionVO : list){
            optionVO.setP_id(productVO.getP_id());
            this.productMapper.insertOption2(optionVO);
            //this.productMapper.updateOption(optionVO);
        }
        return map;
    }

}
