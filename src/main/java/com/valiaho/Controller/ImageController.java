package com.valiaho.Controller;

import com.valiaho.Controller.Exceptions.ImageNotFoundException;
import com.valiaho.Controller.Exceptions.ImageNotPersistedException;
import com.valiaho.Domain.Image;
import com.valiaho.Domain.MultipartFileInformationWrapper;
import com.valiaho.Service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * Created by akivv on 7.4.2016.
 */
@RequestMapping("/api/image")
@ComponentScan("com.valiaho.Service")
@Controller
public class ImageController {
    @Autowired
    ImageService imageService;
    private Logger logger = LoggerFactory.getLogger(ImageController.class);

    @RequestMapping(value = "/findImage/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Image findImageById(@PathVariable("id") String id) {
        final Optional<Image> imageById = imageService.getImageById(id);
        if (!imageById.isPresent()) {
            throw new ImageNotFoundException();
        }
        return imageById.get();
    }

    /**
     * @param multipartFileInfo
     * @return
     */
    @RequestMapping(value = "/postNewImage", method = RequestMethod.POST)
    @ResponseBody
    public Image postImageToService(@RequestPart("Image") MultipartFile file, @ModelAttribute MultipartFileInformationWrapper multipartFileInfo) {
        /* Post image to service with persistence */
        final Optional<Image> image = imageService.persistImage(file,
                multipartFileInfo.getOriginalFilename(), multipartFileInfo.getCommentList());
        if (!image.isPresent()) {
            this.logger.error("Image was not persisted!");
            throw new ImageNotPersistedException();
        }
        return image.get();
    }
}
