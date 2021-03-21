package com.beaverapi.service;

import com.beaverapi.model.User;
import com.beaverapi.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public Page<User> getAllPagingUser(Integer page, Integer size){
        return userRepository.findAll(PageRequest.of(page, size));
    }

    public Optional<User> getUserById(Integer id){
        return userRepository.findById(id);
    }

    public void deletePerson(Integer id){
        userRepository.deleteById(id);
    }

    public User updateUser(Integer id, User user){
        User oldUser = userRepository.getOne(id);
        oldUser.setFirstname(user.getFirstname());
        oldUser.setLastname(user.getLastname());
        return userRepository.save(oldUser);
    }

}