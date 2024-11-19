package com.example.demo.service;

import com.example.demo.dto.FullnameDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.exceptions.EmailTakenException;
import com.example.demo.exceptions.UserAlreadyExistsException;
import com.example.demo.exceptions.UserDoesNotExistException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


//TODO add @RequiredArgsConstructor and avoid using @Autowired for all classes

@Service
@RequiredArgsConstructor
public class UserService {
    private UserMapper userMapper;
    private UserRepository userRepository;

    // Add (C in Crud)
    @Transactional
    public ResponseEntity<Object> addUser(User user) {
        userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new EmailTakenException("The email is already taken!"));

        userRepository.findByFullname(user.getFirstName(), user.getMiddleName(), user.getLastName())
                .orElseThrow(() -> new UserAlreadyExistsException("This person already exists!"));

        //TODO
        //any unique identifier
//        userRepository.findById()
//                        .orElseThrow();

        userRepository.save(user);
        Map<String, Object> details = new HashMap<>();
        details.put("message", "User has been successfully added!");
        details.put("code", HttpStatus.OK.value());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(details);
    }

    // Read operation (R in CRUD)
    //TODO user better naming scheme like getAllUsers
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    //TODO: u can simply use findById.orELsethrow

    public ResponseEntity<Optional<User>> getUser(UUID userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserDoesNotExistException("This person does not exist!"));
        return ResponseEntity.ok(userRepository.findById(userId));
    }


    public ResponseEntity<List<UserDTO>> getUserNoId() {
        return ResponseEntity.ok(userRepository.findAll().stream().map(user -> userMapper.userToUserNoId(user)).collect(Collectors.toList()));
    }

    public ResponseEntity<List<FullnameDTO>> getFullName() {
        return ResponseEntity.ok(userRepository.findAll().stream().map(user -> userMapper.userToFullname(user)).collect(Collectors.toList()));
    }

    // Update operation (U in CRUD)
    @Transactional
    public ResponseEntity<Object> updateUser(User user) {
        userRepository.findById(user.getUserId())
                .orElseThrow(() -> new UserDoesNotExistException("This person does not exist!"));
        userRepository.save(user);
        Map<String, Object> details = new HashMap<>();
        details.put("message", "User has been successfully updated!");
        details.put("code", HttpStatus.OK.value());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(details);
    }

    //TODO use or else throw
    // Delete operation (D in Crud)
    @Transactional
    public ResponseEntity<Object> deleteUser(UUID userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserDoesNotExistException("This person does not exist!"));
        userRepository.deleteById(userId);
        Map<String, Object> details = new HashMap<>();
        details.put("message", "User has been successfully deleted!");
        details.put("code", HttpStatus.OK.value());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(details);
    }
}
