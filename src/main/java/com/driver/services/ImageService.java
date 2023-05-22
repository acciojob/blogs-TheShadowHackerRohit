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


    /*

    testAddImage(com.driver.test.TestCases)  Time elapsed: 0.011 sec  <<< ERROR!
java.lang.NullPointerException: null
	at com.driver.test.TestCases.testAddImage(TestCases.java:134)

testAddImage1(com.driver.test.TestCases)  Time elapsed: 0.004 sec  <<< ERROR!
java.lang.NullPointerException: null
	at c
     */

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog

        Optional<Blog> optionalBlog = blogRepository2.findById(blogId);

//        if(optionalBlog.isEmpty()){
//            throw new BlogNotFoundException("Invalid Blog Id");
//        }

        Blog blog = optionalBlog.get();

        Image image = new Image(description,dimensions);
        image.setBlog(blog);

        List<Image> imageList = blog.getImageList();
        imageList.add(image);
        blogRepository2.save(blog);


        return image;

    }

    /*
    testDeleteImage(com.driver.test.TestCases)  Time elapsed: 0.008 sec  <<< ERROR!
com.driver.Exception.ImageNotFoundException: Invalid Image Id
	at com.driver.services.ImageService.deleteImage(ImageService.java:48)
	at com.driver.test.TestCases.testDeleteImage(TestCases.java:180)

     */

    public void deleteImage(Integer id){

        Optional<Image> optionalImage = imageRepository2.findById(id);
//        if(optionalImage.isEmpty()){
//            throw new ImageNotFoundException("Invalid Image Id");
//        }
        Image image = optionalImage.get();

        Blog blog = image.getBlog();

        List<Image> imageList = blog.getImageList();

        imageList.remove(image);

        imageRepository2.deleteById(id);


    }

    /*

    testCountImage1(com.driver.test.TestCases)  Time elapsed: 0.011 sec  <<< FAILURE!
org.opentest4j.AssertionFailedError: expected: <15> but was: <17>
	at org.junit.jupiter.api.AssertionUtils.fail(AssertionUtils.java:55)
	at org.junit.jupiter.api.AssertionUtils.failNotEqual(AssertionUtils.java:62)
	at org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:150)
	at org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:145)
	at org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:510)
	at com.driver.test.TestCases.testCountImage1(TestCases.java:193)

     */

    public int countImagesInScreen(Integer imageId, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`

        /*
        countImagesInScreen: given an image id and a screen size,
         find the number of images of given size that can fit completely into the screen with given dimensions.
          For example, a screen with dimensions 4X4, can completely fit 4 images, each having dimensions 2X2.
         */

        Optional<Image> optionalImage = imageRepository2.findById(imageId);
//        if(optionalImage.isEmpty()){
//            throw new ImageNotFoundException("Invalid Image Id");
//        }
        Image image = optionalImage.get();

        String dimension = image.getDimensions();

        // change the screen dimension to the Integers value
        String str[] = screenDimensions.split("X");

        int heightScreen = Integer.valueOf(str[0]);
        int widthScreen = Integer.valueOf(str[1]);

        // change the current Image dimension to the Integers value
        String strImage[] = dimension.split("X");

        int heightImage = Integer.valueOf(strImage[0]);
        int widthImage = Integer.valueOf(strImage[1]);


        int heightRatio = heightScreen/heightImage;
        int widthRatio = widthScreen/widthImage;


        int count = heightRatio * widthRatio;

        return count;
    }
}
