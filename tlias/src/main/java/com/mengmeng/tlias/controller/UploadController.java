package com.mengmeng.tlias.controller;

import com.mengmeng.tlias.pojo.Result;
import com.mengmeng.tlias.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;


@Slf4j
@RestController
public class UploadController {
    //    @PostMapping("/upload")
//    public Result upload(String username, Integer age, MultipartFile image) throws IOException {
//        log.info("文件上传：{},{},{}",username,age,image);
//        String originFileName=image.getOriginalFilename();
//        //获取文件扩展名
//        int index=originFileName.lastIndexOf(".");
//        String extName=originFileName.substring(index);
//        //拼接UUID，使上传上来的文件名唯一，不会发生文件相互覆盖的情况
//        String newFileName=UUID.randomUUID().toString() +extName;
//        log.info("新的文件名: {}",newFileName);
//        image.transferTo(new File("F:\\work3\\Codes\\Frontend_code\\proj1\\images\\"+newFileName));
//        return Result.success();
//    }
    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传，文件名: {}", image.getOriginalFilename());
        String url = aliOSSUtils.upload(image);
        log.info("文件上传完成，文件访问的url: {}",url);

        return Result.success(url);
    }


}
