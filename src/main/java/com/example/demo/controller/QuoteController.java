package com.example.demo.controller;

import com.example.demo.dto.QuoteDTO;
import com.example.demo.model.Quote;
import com.example.demo.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("quote/api")
@RequiredArgsConstructor
public class QuoteController {
    private final QuoteService quoteService;

    @GetMapping("quote")
    public ResponseEntity<List<Quote>> getQuote(@RequestBody QuoteDTO quoteDTO) {
        return quoteService.getQuote(quoteDTO);
    }
}
