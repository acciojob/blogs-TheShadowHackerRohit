package com.driver.controller;

import com.driver.Exception.BlogNotFoundException;
import com.driver.Exception.ImageNotFoundException;
import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    ImageService imageService;

    @PostMapping("/{blogId}/add-image")
    public ResponseEntity<String> addImage(@PathVariable int blogId, @RequestParam String description, @RequestParam String dimensions) {
        // Add image into the give blog
        Image savedImage = imageService.addImage(blogId,description,dimensions);

        return new ResponseEntity<>("Added image successfully", HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable int id) {
        // delete image using deleteById
       // try {
            imageService.deleteImage(id);
//        }catch (ImageNotFoundException e){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/countImagesInScreen/{id}/{screenDimensions}")
    public ResponseEntity<Integer> countImagesInScreen(@PathVariable int id, @PathVariable String screenDimensions){
        int count;
       // try {
            count = imageService.countImagesInScreen(id,screenDimensions);
//        }catch (ImageNotFoundException e){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

}



