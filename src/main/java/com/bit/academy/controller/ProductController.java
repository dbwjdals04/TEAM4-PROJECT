
package com.bit.academy.controller;

import com.bit.academy.model.OptionVO;
import com.bit.academy.model.ProductVO;
import com.bit.academy.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.bit.academy.service.UploadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
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


    //상품검색(세부)
    @ResponseBody
    @GetMapping("/product/searchProduct")
    public ProductVO searchProduct(ProductVO productVO,
                                   @RequestParam("c_no") Integer c_no
                                   ){
        log.debug(String.valueOf(c_no));
        ProductVO result = this.productService.productSearch(c_no);

        return result;
    }

    @GetMapping("/admin/productList")
    public String productlist(Model model){

        /*model.addAttribute("productList", this.productService.productSearchAll());
        List<ProductVO> productList = new ArrayList<>();

        for(ProductVO productVO:productList){

        }*/
        return "admin/productList";
    }

    //카테고리별 상품 리스트 출력
    @ResponseBody
    @PostMapping("/admin/productList/{category_no}")
    public List<ProductVO> productList(Model model, @PathVariable String category_no){
        List c_noList = Arrays.asList(category_no.split(","));
        List<ProductVO> List = this.productService.productSearchAll(c_noList);
        return List;
    };

    //상품 상세정보 출력
    @ResponseBody
    @PostMapping("/admin/product/{p_id}")
    public Map<String, Object> SelectProduct(Model model, @PathVariable int p_id){
        Map<String, Object> map= this.productService.productDetail(p_id);
        return map;
    }


    //상품등록
    @PostMapping("/admin/productList/add")
    public String productAdd (@ModelAttribute ProductVO productVO, @ModelAttribute OptionVO optionVO, Model model,
                              @RequestParam("thumbnail") MultipartFile thumbnail, @RequestParam("detailImg") MultipartFile imageFile, @RequestParam("category_no") Integer category_no){
        log.debug("----------------------"+optionVO);
        String returnValue = "start";
        try {
            //this.productService.insertProduct(productVO);
            this.productService.insertProduct(this.uploadService.saveImage(thumbnail, imageFile,productVO), category_no, optionVO);
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
