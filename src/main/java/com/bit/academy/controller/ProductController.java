
package com.bit.academy.controller;

import com.bit.academy.model.ProductVO;
import com.bit.academy.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
public class ProductController {
    @Autowired

    private ProductService productService;

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
}
//(@RequestBody List fileVOList)