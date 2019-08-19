package com.bit.academy.service;

import com.bit.academy.model.ProductVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {
    ProductVO saveImage(MultipartFile thumbnail, MultipartFile detailImg,ProductVO productVO) throws Exception;
    String imgResize(String thumbFolder, String newThumbnailName,int size) throws IOException;

}