package com.driver.services;

import com.driver.Exception.BlogNotFoundException;
import com.driver.Exception.ImageNotFoundException;
import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog

        Optional<Blog> optionalBlog = blogRepository2.findById(blogId);

        if(optionalBlog.isEmpty()){
            throw new BlogNotFoundException("Invalid Blog Id");
        }

        Blog blog = optionalBlog.get();

        Image image = new Image(description,dimensions);

        List<Image> imageList = blog.getImageList();
        imageList.add(image);


       Image savedImage =  imageRepository2.save(image);

        return savedImage;

    }

    public void deleteImage(Integer id){

        Optional<Image> optionalImage = imageRepository2.findById(id);
        if(optionalImage.isEmpty()){
            throw new ImageNotFoundException("Invalid Image Id");
        }
        Image image = optionalImage.get();

        Blog blog = image.getBlog();

        List<Image> imageList = blog.getImageList();

        imageList.remove(image);

        imageRepository2.deleteById(id);


    }

    public int countImagesInScreen(Integer imageId, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`

        /*
        countImagesInScreen: given an image id and a screen size,
         find the number of images of given size that can fit completely into the screen with given dimensions.
          For example, a screen with dimensions 4X4, can completely fit 4 images, each having dimensions 2X2.
         */

        Optional<Image> optionalImage = imageRepository2.findById(imageId);
        if(optionalImage.isEmpty()){
            throw new ImageNotFoundException("Invalid Image Id");
        }
        Image image = optionalImage.get();

        String dimension = image.getDimension();

        // change the screen dimension to the Integers value
        String str[] = screenDimensions.split("X");

        int heightScreen = Integer.valueOf(str[0]);
        int widthScreen = Integer.valueOf(str[1]);

        int productScreen = heightScreen * widthScreen ;



        // change the current Image dimension to the Integers value
        String strImage[] = dimension.split("X");

        int heightImage = Integer.valueOf(strImage[0]);
        int widthImage = Integer.valueOf(strImage[1]);

        int productImage = heightImage * widthImage;



        int count = productScreen/productImage;

        return count;
    }
}
