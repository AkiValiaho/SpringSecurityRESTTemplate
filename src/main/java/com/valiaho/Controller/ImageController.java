package com.valiaho.Controller;

import com.valiaho.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by akivv on 7.4.2016.
 */
@RequestMapping("/api/image")
@ComponentScan("com.valiaho.Service")
public class ImageController {
    @Autowired
    ImageService imageService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public MultipartFile getImage() {
        return null;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public void uploadImage(@RequestParam MultipartFile file) throws IOException {
        imageService.uploadImage(file);
    }
}
