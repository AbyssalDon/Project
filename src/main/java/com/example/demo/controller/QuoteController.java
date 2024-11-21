package com.example.demo.controller;

import com.example.demo.dto.QuoteDTO;
import com.example.demo.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.QuoteApi;
import org.openapitools.model.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuoteController implements QuoteApi {
    private final QuoteService quoteService;

    public ResponseEntity<List<org.openapitools.model.Quote>> getQuote(@RequestBody QuoteDTO quoteDTO) {
        return quoteService.getQuote(quoteDTO);
    }
}
