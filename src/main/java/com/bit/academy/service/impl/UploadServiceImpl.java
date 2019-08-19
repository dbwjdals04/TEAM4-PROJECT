package com.bit.academy.service.impl;

import com.bit.academy.model.ProductVO;
import com.bit.academy.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
public class UploadServiceImpl implements UploadService {

    @Override
    public ProductVO saveImage(MultipartFile thumbnail, MultipartFile detailImg, ProductVO productVO) throws Exception {
        UUID uid = UUID.randomUUID();
        String newThumbnailName = null;
        String newDetailImgName = null;
        String thumbFolder=System.getProperty("user.dir")+"/src/main/resources/uploads/thumbnail/";
        String detailImgFolder=System.getProperty("user.dir")+"/src/main/resources/uploads/detailImg/";

        if(thumbnail.isEmpty() && detailImg.isEmpty()){
            return productVO;
        }else{
            if(!thumbnail.isEmpty()) {
                newThumbnailName = "thumb_"+uid + "_" + thumbnail.getOriginalFilename();
                byte[] thumbBytes= thumbnail.getBytes();
                Path thumbPath = Paths.get(thumbFolder + newThumbnailName);
                Files.createDirectories(Paths.get(thumbFolder));
                Files.write(thumbPath, thumbBytes);
                productVO.setThumb_name(newThumbnailName);
                productVO.setThumb_route(thumbFolder);
                productVO.setThumb_100(imgResize(thumbFolder, newThumbnailName, 100));
                productVO.setThumb_300(imgResize(thumbFolder, newThumbnailName, 300));
                productVO.setThumb_600(imgResize(thumbFolder, newThumbnailName, 600));
            }
            if(!detailImg.isEmpty()){
                newDetailImgName = "detailImg_" + uid + "_" + detailImg.getOriginalFilename();
                byte[] detailImgBytes = detailImg.getBytes();
                Path detailImgPath = Paths.get(detailImgFolder + newDetailImgName);
                Files.createDirectories(Paths.get(detailImgFolder));
                Files.write(detailImgPath, detailImgBytes);
                productVO.setDetailimg_name(newDetailImgName);
                productVO.setDetailimg_route(detailImgFolder);
            }

            log.debug(productVO.toString());
            return productVO;
        }


    }

    @Override
    public String imgResize(String thumbFolder, String newThumbnailName,int size) throws IOException {

        File image = new File(thumbFolder+newThumbnailName);
        String name = size + newThumbnailName;

        File thumb = new File(thumbFolder+name);

        Thumbnails.of(image).crop(Positions.CENTER).size(size, size).toFile(thumb);

        return name;
    }



}