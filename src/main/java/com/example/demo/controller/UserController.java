package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.AddressService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.UserApi;
import org.openapitools.model.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController implements UserApi {
    private final UserService userService;
    private final AddressService addressService;

    @GetMapping("/")
    public ResponseEntity<Response> getUserAll() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "{userId}")
    public ResponseEntity<Response> getUser(@PathVariable("userId") UUID userId) {
        return userService.getUser(userId);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Response> deleteUser(@RequestBody User user) {
        return userService.deleteUser(user);
    }

    @GetMapping("usernoid")
    public ResponseEntity<Response> getUserNoId() {
        return userService.getUserNoId();
    }

    @GetMapping("fullname")
    public ResponseEntity<Response> getUserFullname() {
        return userService.getFullName();
    }

    @PutMapping("update")
    public ResponseEntity<Response> updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @PostMapping("add")
    public ResponseEntity<Response> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("addresses")
    public ResponseEntity<Response> getAddress() {
        return addressService.getUsersAddresses();
    }
}
