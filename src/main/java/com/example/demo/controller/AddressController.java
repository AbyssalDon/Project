package com.example.demo.controller;

import com.example.demo.model.Address;
import com.example.demo.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("address/api")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PostMapping("add")
    public ResponseEntity<Object> addAddress(@RequestBody Address address) {
        return addressService.addAddress(address);
    }

    @GetMapping("/")
    public ResponseEntity<List<Address>> getAddresses() {
        return addressService.getAddresses();
    }
}
