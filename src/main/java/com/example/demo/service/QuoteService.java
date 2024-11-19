package com.example.demo.service;

import com.example.demo.dto.QuoteDTO;
import com.example.demo.exceptions.FilterBadTypeException;
import com.example.demo.model.Quote;
import com.example.demo.repository.QuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuoteService {
    private final QuoteRepository quoteRepository;

    @Value(("${api.url}"))
    private String api;

    //TODO api url should be hidden and not exposed
    public Quote receiveQuote() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(this.api, Quote.class);
    }

    public ResponseEntity<List<Quote>> getQuote(QuoteDTO quoteDTO) {
        return ResponseEntity.ok(switch (quoteDTO.getType()) {
            case "id" ->
                    quoteRepository.findAll().stream().filter(quote -> quote.get_id().equals(quoteDTO.getValue())).toList();
            case "author" ->
                    quoteRepository.findAll().stream().filter(quote -> quote.getAuthorSlug().equals(quoteDTO.getValue())).toList();
            case "tag" -> quoteRepository.findAll().stream().filter(quote -> quoteDTO.isMatch(Arrays.stream(quote.getTags()).toList())).toList();
            default -> throw new FilterBadTypeException("The filter type is wrong or not supported.");
        });
    }
}
