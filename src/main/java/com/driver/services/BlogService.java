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

        /*

         Appointment appointment = AppointmentTransformer.modelsToAppointment(appointmentRequestDto,user,doctor);


       user.getAppointments().add(appointment);
       User savedUser =  userRepository.save(user);// save dose1 and dose2 and appointment

        Appointment savedAppointment = savedUser.getAppointments().get(savedUser.getAppointments().size()-1);

        doctor.getAppointments().add(savedAppointment);
        doctorRepository.save(doctor);

         */


        Blog blog = new Blog(title,content);


        Optional<User> optionalUser = userRepository1.findById(userId);

        User user = optionalUser.get();

        user.getBlogList().add(blog);
        User savedUser =  userRepository1.save(user);

        Blog savedBlog = savedUser.getBlogList().get(savedUser.getBlogList().size()-1);


        Blog finalSavedBlog = blogRepository1.save(savedBlog);

        return finalSavedBlog;
    }

    /*

    testDeleteBlog(com.driver.test.TestCases)  Time elapsed: 0.003 sec  <<< ERROR!
com.driver.Exception.BlogNotFoundException: Invalid Blog Id
	at com.driver.services.BlogService.deleteBlog(BlogService.java:49)
	at com.driver.test.TestCases.testDeleteBlog(TestCases.java:174)

     */

    public void deleteBlog(int blogId){
        //delete blog and corresponding images

        Optional<Blog> optionalBlog = blogRepository1.findById(blogId);

//        if(optionalBlog.isEmpty()){
//            throw new BlogNotFoundException("Invalid Blog Id");
//        }

        Blog blog = optionalBlog.get();

        User user = blog.getUser();

        List<Blog> blogList = user.getBlogList();

        //removing the blog from user wala blockList by remove by object method
        blogList.remove(blog);



        //deleting the blog from blog repo
        blogRepository1.deleteById(blogId);



    }
}
