package com.example.demo.controller;

import com.example.demo.dtos.ResponseStatus;
import com.example.demo.dtos.SignUpUserDtoResponse;
import com.example.demo.dtos.SignUpUserRequestDto;
import com.example.demo.model.classes.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    public SignUpUserDtoResponse sinUps(SignUpUserRequestDto request){
        User user = userService.signUp(request);
        return new SignUpUserDtoResponse(user.getId(), ResponseStatus.SUCCESS);
    }
    public boolean login(String name,String passwoord){
        return userService.login(name, passwoord);
    }

}
