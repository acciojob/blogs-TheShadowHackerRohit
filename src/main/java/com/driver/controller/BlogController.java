package com.driver.controller;

import com.driver.Exception.BlogNotFoundException;
import com.driver.Exception.UserNotFoundException;
import com.driver.models.Blog;
import com.driver.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    BlogService blogService;

    @PostMapping("/createBlog")
    public ResponseEntity createBlog(@RequestParam Integer userId , @RequestParam String title, @RequestParam String content) {
        // Create a blog and add it under given user

        //try {
            Blog savedBlog = blogService.createAndReturnBlog(userId,title,content);
//        }catch (UserNotFoundException e){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{blogId}")
    public ResponseEntity<Void> deleteBlog(@PathVariable int blogId) {
        // Delete the blog using deleteById
        //try {
            blogService.deleteBlog(blogId);
//        }catch (BlogNotFoundException e){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}




