package com.user_project.user_api.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {


    private Map<Integer, User> users = new HashMap<>();

    public UserController() {
        addUser("Billy", "OR",1);
        addUser("julie", "CA",2);
        addUser("drew", "NY",3);
        addUser("jack", "PA",4);
    }

    private void addUser(String name, String location, Integer id){
        User user = new User(name, location, id);
        users.put(id,user);
        System.out.println(users);
    }



    @GetMapping
    public Map<Integer, User> getAllUsers(){
        return this.users;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id){
        return users.get(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        addUser(user.getName(), user.getLocation(), user.getId());
        return users.get(user.getId());
    }

    @PutMapping
    public User updateUser(@RequestBody User user){
        User userToUpdate = users.get(user.getId());
        userToUpdate.setName(user.getName());
        userToUpdate.setName(user.getLocation());
        return users.get(userToUpdate.getId());
    }

    @DeleteMapping("/{id}")
    public Map<Integer, User> deleteUser(@PathVariable Integer id){
        users.remove(id);
        return users;
    }

}
