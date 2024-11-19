package com.example.demo.service;

import com.example.demo.dto.FullnameDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.UserNoIdDTO;
import com.example.demo.exceptions.EmailTakenException;
import com.example.demo.exceptions.FilterBadTypeException;
import com.example.demo.exceptions.PersonAlreadyExistsException;
import com.example.demo.exceptions.PersonDoesNotExistException;
import com.example.demo.mapper.ResponseMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Address;
import com.example.demo.dto.FilterDTO;
import com.example.demo.model.Quote;
import com.example.demo.model.User;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.QuoteRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


//TODO add @RequiredArgsConstructor and avoid using @Autowired for all classes

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    ResponseMapper responseMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private QuoteRepository quoteRepository;



    private int execTimes = 0;

    @Value("${timer.maxrecords}")
    private int records;

    // Add (C in Crud)
    @Transactional
    public void addUser(User user) {
        if (userRepository.existsByEmail(user.getEmail()))
            throw new EmailTakenException("The email is already taken!");
        if (userRepository.existsByFirstName(user.getFirstName())
            && userRepository.existsByMiddleName(user.getMiddleName())
            && userRepository.existsByLastName(user.getLastName()))
            throw new PersonAlreadyExistsException("This person already exists!");

        //TODO
        //any unique identifier
//        userRepository.findById()
//                        .orElseThrow();

        userRepository.save(user);
    }

    // Read operation (R in CRUD)
    //TODO user better naming scheme like getAllUsers
    public List<User> getUserAll() {
        return userRepository.findAll();
    }

    //TODO: u can simply use findById.orELsethrow

    public Optional<User> getUser(UUID userId) {
        if (!userRepository.existsById(userId))
            throw new PersonDoesNotExistException("This person does not exist!");

        return userRepository.findById(userId);
    }


    public List<UserNoIdDTO> getUserNoId() {
        return userRepository.findAll().stream().map(user -> userMapper.userToUserNoId(user)).collect(Collectors.toList());
    }

    public List<FullnameDTO> getFullName() {
        return userRepository.findAll().stream().map(user -> userMapper.userToFullname(user)).collect(Collectors.toList());
    }

    // Update operation (U in CRUD)
    @Transactional
    public void updateUser(User user) {
        if(!userRepository.existsById(user.getUserId()))
            throw new PersonDoesNotExistException("This person does not exist!");
        userRepository.save(user);
    }

    //TODO use or else throw
    // Delete operation (D in Crud)
    @Transactional
    public void deleteUser(UUID userId) {
        if(!userRepository.existsById(userId))
            throw new PersonDoesNotExistException("This person does not exist!");

        userRepository.deleteById(userId);
    }

    //TODO no validation
    @Transactional
    public void addAddress(Address address) {
        addressRepository.save(address);
    }

    public List<Address> getAddresses() {
        return addressRepository.findAll();
    }

    public List<ResponseDTO> getUserAddress() {
        return userRepository.findAll().stream().map(user -> responseMapper.mapToResponse(addressRepository.findByUserId(user.getUserId()), user)).collect(Collectors.toList());
    }

    //TODO api url should be hidden and not exposed
    private Quote receiveQuote() {
        String api = "http://api.quotable.io/random";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(api, Quote.class);
    }

    //TODO : the count should be taken from database not memory
    //TODO :  move this to sepreate class
    @Scheduled(fixedDelay = 60000)
    public void saveQuote() {
        if (this.execTimes < this.records) {
            quoteRepository.save(this.receiveQuote());
            this.execTimes++;
        }
    }

    public List<Quote> getQuote(FilterDTO filterDTO) {
        return switch (filterDTO.getType()) {
            case "id" ->
                    quoteRepository.findAll().stream().filter(quote -> quote.get_id().equals(filterDTO.getValue())).toList();
            case "author" ->
                    quoteRepository.findAll().stream().filter(quote -> quote.getAuthorSlug().equals(filterDTO.getValue())).toList();
            case "tag" -> quoteRepository.findAll().stream().filter(quote -> filterDTO.contains(quote.getTags())).toList();
            default -> throw new FilterBadTypeException("The filter type is wrong or not supported.");
        };
    }
}
