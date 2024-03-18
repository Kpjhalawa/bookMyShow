package com.example.demo.services;

import com.example.demo.dtos.SignUpUserRequestDto;
import com.example.demo.model.classes.User;
import com.example.demo.repository.UserRepositiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepositiory userRepositiory;

    public User signUp(SignUpUserRequestDto request){
        //we want to make sure this is a new user.
        Optional<User> user = userRepositiory.findByEmail(request.getEmail());
        if(user.isPresent()){
            throw new RuntimeException();
        }
        User newUser = new User();
        newUser.setName(request.getName());
        newUser.setEmail(request.getEmail());
        String password = request.getPasswords();
       BCryptPasswordEncoder encoder=
               new BCryptPasswordEncoder();
       newUser.setPassword(encoder.encode(password));
        return userRepositiory.save(newUser);
    }
    public boolean login(String email,String password){
        Optional<User> user = userRepositiory.findByEmail(email);
        if(!user.isPresent()){
            throw new RuntimeException();
        }
        BCryptPasswordEncoder encoder =
                new BCryptPasswordEncoder();
        return encoder.matches(password,user.get().getPassword());
    }
}
