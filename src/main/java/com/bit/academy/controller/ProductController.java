
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
import java.util.*;

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

    @GetMapping("/admin/productList")
    public String productlist(Model model){
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
        Map<String, Object> map= this.productService.selectProduct(p_id);
        return map;
    }


    //상품등록

//test
//    @PostMapping("/admin/productList/add")
//    public String TestproductAdd (@ModelAttribute OptionVO optionVO, Model model) {
//        OptionVO op1 = new OptionVO();
//        op1.setPo_value("option1");
//        op1.setPo_price(1000);
//        op1.setPo_stock(1);
//        OptionVO op2 = new OptionVO();
//        op2.setPo_value("option2");
//        op2.setPo_price(2000);
//        op2.setPo_stock(2);
//        List<OptionVO> list = new ArrayList<OptionVO>();
//        list.add(op1);
//        list.add(op2);
//        log.debug("=========list=========");
//        log.debug(list.toString());
//        log.debug("===================");
//        //log.debug(optionVO.getOptionVOList().toString());
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("list", list);
//        map.put("p_id", 45);
//        this.productService.insertTest(list);
//        return "admin/productList";
//    }

    @PostMapping("/admin/productList/add")
    public String productAdd (@ModelAttribute ProductVO productVO, @ModelAttribute OptionVO optionVO, Model model,
                              @RequestParam("thumbnail") MultipartFile thumbnail, @RequestParam("detailImg") MultipartFile imageFile, @RequestParam("category_no") Integer category_no){

        String returnValue = "start";
        try {
            this.productService.insertProduct(this.uploadService.saveImage(thumbnail, imageFile,productVO), category_no, optionVO.getOptionVOList());
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
        model.addAttribute("product", this.productService.productDetail(p_id));
        return "/product/productDetail";
    }

}
