package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.exceptions.EmailTakenException;
import com.example.demo.exceptions.UserDoesNotExistException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.UserApi;
import org.openapitools.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserApi {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    // Add (C in Crud)
    @Transactional
    public ResponseEntity<Response> addUser(User user) {
        if (userRepository.existsByEmail(user.getEmail()))
            throw new EmailTakenException("The email is already taken!");

        userRepository.save(userMapper.userToUser(user));
        Response response = new Response();
        return ResponseEntity.ok(
                response.code(HttpStatus.OK.value())
                        .message("User has been successfully added!")
                        .content(user)
        );
    }

    // Read operation (R in CRUD)
    public ResponseEntity<List<org.openapitools.model.User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll().stream().map(user -> userMapper.userToUser(user)).collect(Collectors.toList()));
    }

    public ResponseEntity<org.openapitools.model.User> getUser(UUID userId) {
        return ResponseEntity.ok(userRepository.findById(userId).map(user -> userMapper.userToUser(user)).orElseThrow(() -> new UserDoesNotExistException("This person does not exist!")));
    }


    public ResponseEntity<List<UsernoidDTO>> getUserNoId() {
        return ResponseEntity.ok(userRepository.findAll().stream().map(user -> userMapper.userToUserNoId(user)).map(userDTO -> userMapper.userNoIdToUserNoId(userDTO)).collect(Collectors.toList()));
    }

    public ResponseEntity<List<FullnameDTO>> getFullName() {
        return ResponseEntity.ok(userRepository.findAll().stream().map(user -> userMapper.userToFullname(user)).map(fullnameDTO -> userMapper.fullnameToFullname(fullnameDTO)).collect(Collectors.toList()));
    }

    // Update operation (U in CRUD)
    @Transactional
    public ResponseEntity<Response> updateUser(org.openapitools.model.User user) {
        userRepository.findById(user.getUserId())
                .orElseThrow(() -> new UserDoesNotExistException("This person does not exist!"));
        userRepository.save(userMapper.userToUser(user));
        Response response = new Response();
        return ResponseEntity.ok(
                response.code(HttpStatus.OK.value())
                        .content(user)
                        .message("user successfully updated")
        );
    }

    // Delete operation (D in CRUD)
    @Transactional
    public ResponseEntity<Response> deleteUser(org.openapitools.model.User user) {
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
