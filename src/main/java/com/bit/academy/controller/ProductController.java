
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

import java.util.List;

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

    @ResponseBody
    @GetMapping("/product/searchProductAll")
    public ProductVO searchProductAll(ProductVO productVO,
                                      @RequestParam("c_no[]") List c_no,
                                      Model model)
    {

        log.debug(String.valueOf(c_no));

        ProductVO result = this.productService.productSearchAll(c_no);

        return result;
    }

    @ResponseBody
    @GetMapping("/product/searchProduct")
    public ProductVO searchProduct(ProductVO productVO,
                                   @RequestParam("c_no") Integer c_no
                                   ){
        log.debug(String.valueOf(c_no));
        ProductVO result = this.productService.productSearch(c_no);

        return result;
    }

    @GetMapping("/admin/product")
    public String productlist(){
        return "admin/productList";
    }

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
        }
        return "admin/productList";
    }
}
//(@RequestBody List fileVOList)