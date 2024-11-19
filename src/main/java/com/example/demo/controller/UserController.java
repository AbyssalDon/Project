package com.example.demo.controller;

import com.example.demo.dto.FullnameDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.model.User;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.AddressService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//TODO: use consistent naming for the controller class and return types
//TODO: use consistent endpoints
//TODO: separate user / address / quote in separate controllers
@RestController
@RequestMapping("user/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AddressService addressService;

    @GetMapping("/")
    public ResponseEntity<List<User>> getUserAll() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "{userId}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable("userId") UUID userId) {
        return userService.getUser(userId);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Object> deleteUser(@RequestBody UUID userId) {
        return userService.deleteUser(userId);
    }

    @GetMapping("usernoid")
    public ResponseEntity<List<UserDTO>> getUserNoId() {
        return userService.getUserNoId();
    }

    @GetMapping("fullname")
    public ResponseEntity<List<FullnameDTO>> getUserFullname() {
        return userService.getFullName();
    }

    @PutMapping("update")
    public ResponseEntity<Object> updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @PostMapping("add")
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("addresses")
    public ResponseEntity<List<ResponseDTO>> getAddress() {
        return addressService.getUsersAddresses();
    }
}
