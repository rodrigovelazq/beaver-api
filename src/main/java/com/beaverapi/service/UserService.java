package com.beaverapi.service;

import com.beaverapi.model.User;
import com.beaverapi.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder,
                       UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public Page<User> getAllPagingUser(Integer page, Integer size, Optional<String> orderBy, Optional<Boolean> asc) {
        if (orderBy.isPresent()) {
            Sort sort = asc.get() ? Sort.by(orderBy.get()).ascending() : Sort.by(orderBy.get()).descending();
            return userRepository.findAll(
                    PageRequest.of(
                            page,
                            size,
                            sort
                    )
            );
        }
        return userRepository.findAll(PageRequest.of(page, size));
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public void deletePerson(Integer id) {
        userRepository.deleteById(id);
    }

    public User updateUser(Integer id, User user) {
        User oldUser = userRepository.getOne(id);
        oldUser.setFirstname(user.getFirstname());
        oldUser.setLastname(user.getLastname());
        return userRepository.save(oldUser);
    }

}