package com.example.demo.controller;

import com.example.demo.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.AddressApi;
import org.openapitools.model.Address;
import org.openapitools.model.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class AddressController implements AddressApi {
    private final AddressService addressService;

    @Override
    public ResponseEntity<Response> addAddress(@RequestBody Address address) {
        return addressService.addAddress(address);
    }

    @Override
    public ResponseEntity<List<org.openapitools.model.Address>> getAllAddresses() {
        return addressService.getAllAddresses();
    }
}
