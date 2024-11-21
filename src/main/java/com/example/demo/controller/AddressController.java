package com.example.demo.controller;

import com.example.demo.model.Address;
import com.example.demo.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.AddressApi;
import org.openapitools.model.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/address")
@RequiredArgsConstructor
public class AddressController implements AddressApi {
    private final AddressService addressService;

    @PostMapping("add")
    public ResponseEntity<Response> addAddress(@RequestBody Address address) {
        return addressService.addAddress(address);
    }

    @GetMapping("")
    public ResponseEntity<Response> getAddresses() {
        return addressService.getAllAddresses();
    }
}
