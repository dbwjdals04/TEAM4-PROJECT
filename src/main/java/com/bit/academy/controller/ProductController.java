
package com.bit.academy.controller;

import com.bit.academy.model.ProductVO;
import com.bit.academy.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.bit.academy.service.UploadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UploadService uploadService;

    @GetMapping("/product/main")
    public String product(){
        return "/product/productMain";
    }
    //상품검색(카테고리)
    @ResponseBody
    @GetMapping("/product/searchProductAll")
    public List<ProductVO> searchProductAll(ProductVO productVO,
                                      @RequestParam("c_no[]") List c_no,
                                      Model model){
        log.debug(String.valueOf(c_no));
        List<ProductVO> product = this.productService.productSearchAll(c_no);

        return product;
    }
    //상품검색(세부)
    @ResponseBody
    @GetMapping("/product/searchProduct")
    public List<ProductVO> searchProduct(ProductVO productVO,
                                   @RequestParam("c_no") Integer c_no
                                   ){
        log.debug(String.valueOf(c_no));
        List<ProductVO> product = this.productService.productSearch(c_no);
        return product;
    }

    @GetMapping("/admin/product")
    public String productlist(){
        return "admin/productList";
    }
    //상품등록
    @PostMapping("/admin/product/add")
    public String productAdd (@ModelAttribute ProductVO productVO, Model model,
                              @RequestParam("thumbnail") MultipartFile thumbnail, @RequestParam("detailImg") MultipartFile imageFile){
        String returnValue = "start";
        try {
            //this.productService.insertProduct(productVO);
            //log.debug(this.uploadService.saveImage(imageFile, productVO).toString());
            this.productService.insertProduct(this.uploadService.saveImage(thumbnail, imageFile,productVO));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error saving photo ", e);
            returnValue = "error";
            //(@RequestBody List fileVOList)
        }
        return "admin/productList";
    }
    @GetMapping("/product/productDetail")
    public String productDetail(Integer p_id, Model model){
        Map<String, Object> map= this.productService.productDetail(p_id);
        model.addAttribute("product", map);
        return "/product/productDetail";
    }

}
