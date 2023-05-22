package com.driver.services;

import com.driver.Exception.BlogNotFoundException;
import com.driver.Exception.UserNotFoundException;
import com.driver.models.Blog;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    UserRepository userRepository1;

    /*

    testCreateAndReturnBlog(com.driver.test.TestCases)  Time elapsed: 0.761 sec  <<< ERROR!
java.lang.NullPointerException: null
	at com.driver.test.TestCases.testCreateAndReturnBlog(TestCases.java:86)


testCreateAndReturnBlog1(com.driver.test.TestCases)  Time elapsed: 0.006 sec  <<< ERROR!
java.lang.NullPointerException: null
	at com.driver.test.TestCases.testCreateAndReturnBlog1(TestCases.java:105)

     */

    public Blog createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
//
//
//
//        Optional<User> optionalUser = userRepository1.findById(userId);
//        User user = optionalUser.get();
//
//
//        Blog blog = new Blog(title,content);
//        blog.setUser(user);
//
//        user.getBlogList().add(blog);
//        User savedUser =  userRepository1.save(user);
//
//
//        //this uncomments codes are optional
//
////        List<Blog> blogList =savedUser.getBlogList();
////        int size = blogList.size();
////
////        Blog savedBlog = blogList.get(size - 1);
////        Blog finalSavedBlog = blogRepository1.save(savedBlog);
////        return  finalSavedBlog;
//
//        return blog;

        User user=userRepository1.findById(userId).get();
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setPubDate(new Date());
        blog.setUser(user);

        user.getBlogList().add(blog);
        userRepository1.save(user);
        return blog;

    }

    /*

   testDeleteBlog(com.driver.test.TestCases)  Time elapsed: 0.01 sec  <<< ERROR!
java.util.NoSuchElementException: No value present
	at java.base/java.util.Optional.get(Optional.java:148)
	at com.driver.services.BlogService.deleteBlog(BlogService.java:95)
	at com.driver.test.TestCases.testDeleteBlog(TestCases.java:174)

     */

    public void deleteBlog(int blogId){
        //delete blog and corresponding images

//        Optional<Blog> optionalBlog = blogRepository1.findById(blogId);
//        Blog blog = optionalBlog.get();
//
//        User user = blog.getUser();
//
//        List<Blog> blogList = user.getBlogList();
//
//        //removing the blog from user wala blockList by remove by object method
//        blogList.remove(blog);


//
//        //deleting the blog from blog repo
//        blogRepository1.deleteById(blogId);


        blogRepository1.deleteById(blogId);


    }
}
