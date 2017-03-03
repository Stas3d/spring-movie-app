package com.epam.springapp.mvc;

import com.epam.springapp.dataModel.User;
import com.epam.springapp.user.UserServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "users")
public class UsersController {

    @Autowired
    private UserServiceManager userServiceManager;

    @ResponseBody
    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<User> receiveAllUsers() {
        return userServiceManager.findAllUsers();
    }

    @ResponseBody
    @RequestMapping(value = "/getUserById/{value}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public User receiveUserById(@PathVariable long value) {
        return userServiceManager.getUserById(value);
    }

    @ResponseBody
    @RequestMapping(value = "/getUserByEmail/{value}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public User receiveUserByMail(@PathVariable String value) {
        return userServiceManager.getUserByEmail(value);
    }

    @ResponseBody
    @RequestMapping(value = "/getUserByName/{value}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public User receiveUserByName(@PathVariable String value) {
        return userServiceManager.getUserByName(value);
    }
    
    @ResponseBody
    @RequestMapping(value = "/checkIfUserExists/{value}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public boolean isUserExists(@PathVariable String value) {
        return userServiceManager.checkIfUserExists(value);
    }
    

//    @ResponseBody
//    @RequestMapping(value = "createUser", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public void createUser(HttpServletRequest request)//!!!
//    {
//        String userName = request.getParameter("userName");
//        long userId = Long.parseLong(request.getParameter("userId"));
//        String userMail = request.getParameter("userMail");
//        String birthDate = request.getParameter("birthDate");
//        userServiceManager.registerUser(userName, userId, userMail, birthDate);
//
//    }
}
