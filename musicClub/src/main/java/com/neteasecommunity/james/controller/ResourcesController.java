package com.neteasecommunity.james.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@CrossOrigin
@RestController("/api/resources")
public class ResourcesController {
    @Value("${prop.upload-folder}")
    private String UPLOAD_FOLDER;


//    //获取用户的头像
//    @GetMapping(value = "/image",produces = MediaType.IMAGE_JPEG_VALUE)
//    @ResponseBody
//    public byte[] getImage throws Exception{
//        File file = new File(UPLOAD_FOLDER+"18629025511602039.jpg");
//        FileInputStream inputStream = null;
//        try {
//            inputStream = new FileInputStream(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        byte[] bytes = new byte[inputStream.available()];
//        try {
//            inputStream.read(bytes, 0, inputStream.available());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return bytes;
//
//    }
}
