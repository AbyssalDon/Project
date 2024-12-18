package com.example.demo.service;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.exceptions.AddressNotLinkedException;
import com.example.demo.exceptions.UserDoesNotExistException;
import com.example.demo.exceptions.UserAlreadyHasAddressException;
import com.example.demo.mapper.ResponseMapper;
import com.example.demo.model.Address;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.AddressApi;
import org.openapitools.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService implements AddressApi {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final ResponseMapper responseMapper;

    @Transactional
    public ResponseEntity<Response> addAddress(Address address) {
        Optional.ofNullable(address.getUserId())
                .orElseThrow(() -> new AddressNotLinkedException("An address must be linked to an existing user."));
        userRepository.findById(address.getUserId())
                .orElseThrow(() -> new UserDoesNotExistException("An address cannot be linked to a user that does not exist."));
        if (addressRepository.existsByUserId(address.getUserId()))
            throw new UserAlreadyHasAddressException("Cannot link an address to a user that is already linked to another address.");

        addressRepository.save(address);
        Response response = new Response();
        return ResponseEntity.ok(
                response.code(HttpStatus.OK.value())
                        .content(address)
                        .message("address added successfully")
        );
    }

    public ResponseEntity<Response> getAllAddresses() {
        Response response = new Response();
        return ResponseEntity.ok(
                response.code(HttpStatus.OK.value())
                        .content(addressRepository.findAll())
                        .message("addresses fetched successfully")
        );
    }

    public ResponseEntity<Response> getUsersAddresses() {
        Response response = new Response();
        return ResponseEntity.ok(
                response.code(HttpStatus.OK.value())
                        .content(userRepository.findAll().stream()
                                .map(user -> responseMapper.mapToResponse(addressRepository.findByUserId(user.getUserId()), user))
                                .collect(Collectors.toList()))
                        .message("success")
        );

    }
}
