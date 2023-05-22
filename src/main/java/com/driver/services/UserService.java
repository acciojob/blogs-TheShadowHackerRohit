package com.driver.services;

import com.driver.Exception.UserNotFoundException;
import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository3;

    public User createUser(String username, String password){

        User user = new User(username,password);
        User savedUser = userRepository3.save(user);

        return savedUser;

    }

    /*

    testDeleteUser(com.driver.test.TestCases)  Time elapsed: 0.002 sec  <<< ERROR!
com.driver.Exception.UserNotFoundException: Invalid User Id
	at com.driver.services.UserService.deleteUser(UserService.java:30)
	at com.driver.test.TestCases.testDeleteUser(TestCases.java:272)

     */

    public void deleteUser(int userId){

//        Optional<User> optionalUser = userRepository3.findById(userId);
//
//        if(optionalUser.isEmpty()){
//            throw new UserNotFoundException("Invalid User Id");
//        }

        userRepository3.deleteById(userId);

    }

    /*

    testUpdateUser(com.driver.test.TestCases)  Time elapsed: 0.012 sec  <<< FAILURE!
org.mockito.exceptions.verification.WantedButNotInvoked:
Wanted but not invoked:
userRepository3.save(<any>);
-> at com.driver.test.TestCases.testUpdateUser(TestCases.java:285)

However, there was exactly 1 interaction with this mock:
userRepository3.findById(1);
-> at com.driver.services.UserService.updateUser(UserService.java:39)

     */

    public User updateUser(Integer id, String password){

        Optional<User> optionalUser = userRepository3.findById(id);

//        if(optionalUser.isEmpty()){
//            throw new UserNotFoundException("Invalid User Id");
//        }
        User user = optionalUser.get();
        user.setPassword(password);

        return user;
    }

    /*

    Tests in error:
  TestCases.testCreateAndReturnBlog:86 NullPointer
  TestCases.testCreateAndReturnBlog1:105 NullPointer
  TestCases.testAddImage:134 NullPointer
  TestCases.testAddImage1:165 NullPointer
  TestCases.testDeleteBlog:174 » BlogNotFound Invalid Blog Id
  TestCases.testDeleteImage:180 » ImageNotFound Invalid Image Id
  TestCases.testDeleteUser:272 » UserNotFound Invalid User Id
     */
}
