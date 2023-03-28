package com.nagp.msa.profilemanagement.controller;

import com.nagp.msa.profilemanagement.model.UserDetails;
import com.nagp.msa.profilemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("profile")
public class ProfileController {

    @Autowired
    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDetails> userView(){
        return userService.userList();

    }
    @GetMapping("/user/{username}")
    public UserDetails getUserByName(@PathVariable (name = "username") String userName){
        return userService.getUserByUsername(userName);
    }
    @PutMapping("/login/{username}")
    public String updateUserLogIn(@PathVariable (name = "username") String userName){
        return userService.updateUserLoggedIN(userName);
    }
    @PutMapping("/logout/{username}")
    public String updateUserLogOut(@PathVariable (name = "username") String userName){
        return userService.updateUserLoggedOut(userName);
    }
    @GetMapping("/login/status/{username}")
    public boolean getUserLoggedStatus(@PathVariable (name = "username") String userName){
        return userService.userLoggedStatus(userName);
    }
    @GetMapping("/user/scope/{username}")
    public String getUserType(@PathVariable (name = "username") String userName){
        return userService.getUserType(userName);
    }
    @PostMapping("/add/user")
    public String addUser(@RequestBody UserDetails userDetails){
        return userService.addUser(userDetails);
    }

}
