package com.example.demo.controller;

import com.example.demo.service.AddressService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.UserApi;
import org.openapitools.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {
    private final UserService userService;
    private final AddressService addressService;

    @Override
    public ResponseEntity<List<org.openapitools.model.User>> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public ResponseEntity<org.openapitools.model.User> getUser(@PathVariable("userId") UUID userId) {
        return userService.getUser(userId);
    }

    @Override
    public ResponseEntity<Response> deleteUser(@RequestBody org.openapitools.model.User user) {
        return userService.deleteUser(user);
    }

    @Override
    public ResponseEntity<List<UsernoidDTO>> getUserNoId() {
        return userService.getUserNoId();
    }

    @Override
    public ResponseEntity<List<FullnameDTO>> getUserFullname() {
        return userService.getFullName();
    }

    @Override
    public ResponseEntity<Response> updateUser(@RequestBody org.openapitools.model.User user){
        return userService.updateUser(user);
    }

    @Override
    public ResponseEntity<Response> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @Override
    public ResponseEntity<List<ResponseDTO>> getUserAddresses() {
        return addressService.getUsersAddresses();
    }
}
