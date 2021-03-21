package com.beaverapi.controller;

import com.beaverapi.model.User;
import com.beaverapi.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void addUser(@Valid @NotNull @RequestBody User user) {
        userService.addUser(user);
    }

    @GetMapping
    public Page<User> getAllPagingUser(@RequestParam Integer page, @RequestParam Integer size) {
        return userService.getAllPagingUser(page, size);

    }

    @GetMapping(path = "{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        return userService.getUserById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUserById(@PathVariable("id") Integer id) {
        userService.deletePerson(id);
    }

    @PutMapping(path = "{id}")
    public void updateUser(@PathVariable("id") Integer id, @Valid @NotNull @RequestBody User person) {
        userService.updateUser(id, person);
    }

}