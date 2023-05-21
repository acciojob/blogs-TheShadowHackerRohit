package com.driver.services;

import com.driver.Exception.BlogNotFoundException;
import com.driver.Exception.UserNotFoundException;
import com.driver.models.Blog;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    UserRepository userRepository1;

    public Blog createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time

        Blog blog = new Blog(title,content);
        Blog savedBlog = blogRepository1.save(blog);

        Optional<User> optionalUser = userRepository1.findById(userId);

        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("Invalid User Id");
        }

        User user = optionalUser.get();

        List<Blog> blogList = user.getBlogList();
        blogList.add(savedBlog);

        return savedBlog;
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images

        Optional<Blog> optionalBlog = blogRepository1.findById(blogId);

        if(optionalBlog.isEmpty()){
            throw new BlogNotFoundException("Invalid Blog Id");
        }

        Blog blog = optionalBlog.get();

        User user = blog.getUser();

        List<Blog> blogList = user.getBlogList();

        //removing the blog from user wala blockList by remove by object method
        blogList.remove(blog);



        //deleting the blog from blog repo
        blogRepository1.deleteById(blogId);



    }
}
