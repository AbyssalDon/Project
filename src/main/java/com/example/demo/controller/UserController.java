package com.example.demo.controller;

import com.example.demo.dto.FullnameDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.model.Address;
import com.example.demo.dto.FilterDTO;
import com.example.demo.model.Quote;
import com.example.demo.model.User;
import com.example.demo.dto.UserNoIdDTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> getPersonAll() {
        return userService.getUserAll();
    }


    @GetMapping(path = "user/{userId}")
    public ResponseEntity<Optional<User>> getPerson(@PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @DeleteMapping("api/delete/{userId}")
    public void deletePerson(@PathVariable("userId") UUID userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("usernoid")
    public List<UserNoIdDTO> getPersonNoId() {
        return userService.getUserNoId();
    }

    @GetMapping("fullname")
    public List<FullnameDTO> getPersonFullName() {
        return userService.getFullName();
    }

    @PutMapping("update")
    public void updatePerson(@RequestBody User user){
        userService.updateUser(user);
    }

    @PostMapping("add")
    public void addPerson(@RequestBody User user) {
        userService.addUser(user);
    }

    @PostMapping("address/add")
    public void addAddress(@RequestBody Address address) {
        userService.addAddress(address);
    }

    @GetMapping("addresses")
    public List<Address> getAddresses() {
        return userService.getAddresses();
    }

    @GetMapping("address")
    public List<ResponseDTO> getAddress() {
        return userService.getUserAddress();
    }

    @GetMapping("quote")
    public List<Quote> getQuote(@RequestBody FilterDTO filterDTO) {
        return userService.getQuote(filterDTO);
    }
}
