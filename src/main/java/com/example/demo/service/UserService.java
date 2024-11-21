package com.example.demo.service;

import com.example.demo.dto.FullnameDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.exceptions.EmailTakenException;
import com.example.demo.exceptions.UserDoesNotExistException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    // Add (C in Crud)
    @Transactional
    public ResponseEntity<Response> addUser(User user) {
        if (userRepository.existsByEmail(user.getEmail()))
            throw new EmailTakenException("The email is already taken!");

        userRepository.save(user);
        Response response = new Response();
        return ResponseEntity.ok(
                response.code(HttpStatus.OK.value())
                        .message("User has been successfully added!")
                        .content(user)
        );
    }

    // Read operation (R in CRUD)
    public ResponseEntity<Response> getAllUsers() {
        Response response = new Response();
        return ResponseEntity.ok(
                response.code(HttpStatus.OK.value())
                        .message("users fetched successfully")
                        .content(userRepository.findAll())
        );
    }

    public ResponseEntity<Response> getUser(UUID userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserDoesNotExistException("This person does not exist!"));
        Response response = new Response();
        return ResponseEntity.ok(
                response.code(HttpStatus.OK.value())
                        .content(userRepository.findById(userId))
                        .message("user fetched successfully")
        );
    }


    public ResponseEntity<Response> getUserNoId() {
        Response response = new Response();
        return ResponseEntity.ok(
                response.code(HttpStatus.OK.value())
                        .content(userRepository.findAll().stream().map(userMapper::userToUserNoId).collect(Collectors.toList()))
                        .message("users with no userId fetched successfully")
        );
    }

    public ResponseEntity<Response> getFullName() {
        Response response = new Response();
        return ResponseEntity.ok(
                response.code(HttpStatus.OK.value())
                        .content(userRepository.findAll().stream().map(userMapper::userToFullname).collect(Collectors.toList()))
                        .message("fullnames fetched successfully")
        );
    }

    // Update operation (U in CRUD)
    @Transactional
    public ResponseEntity<Response> updateUser(User user) {
        userRepository.findById(user.getUserId())
                .orElseThrow(() -> new UserDoesNotExistException("This person does not exist!"));
        userRepository.save(user);
        Response response = new Response();
        return ResponseEntity.ok(
                response.code(HttpStatus.OK.value())
                        .content(user)
                        .message("user successfully updated")
        );
    }

    // Delete operation (D in CRUD)
    @Transactional
    public ResponseEntity<Response> deleteUser(User user) {
        userRepository.findById(user.getUserId())
                .orElseThrow(() -> new UserDoesNotExistException("This person does not exist!"));
        userRepository.deleteById(user.getUserId());
        Response response = new Response();
        return ResponseEntity.ok(
                response.code(HttpStatus.OK.value())
                        .content(user)
                        .message("user successfully deleted")
        );
    }
}
