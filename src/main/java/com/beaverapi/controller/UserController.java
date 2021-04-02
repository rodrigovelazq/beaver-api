package com.beaverapi.controller;

import com.beaverapi.model.User;
import com.beaverapi.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
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
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public Page<User> getAllPagingUser(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam Optional<String> orderBy,
            @RequestParam Optional<Boolean> asc) {
        return userService.getAllPagingUser(page, size, orderBy, asc);
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
