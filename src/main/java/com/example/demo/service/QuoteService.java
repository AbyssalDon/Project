package com.example.demo.service;

import com.example.demo.dto.QuoteDTO;
import com.example.demo.exceptions.FilterBadTypeException;
import com.example.demo.model.Quote;
import com.example.demo.repository.QuoteRepository;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.QuoteApi;
import org.openapitools.model.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuoteService implements QuoteApi {
    private final QuoteRepository quoteRepository;

    @Value(("${api.url}"))
    private String api;

    public Quote receiveQuote() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(this.api, Quote.class);
    }

    public ResponseEntity<Response> getQuote(QuoteDTO quoteDTO) {
        Response response = new Response();
        response.code(HttpStatus.OK.value())
                .message("quotes fetched successfully");
        return ResponseEntity.ok(switch (quoteDTO.getType()) {
            case "id" ->
                    response.content(quoteRepository.findAll().stream().filter(quote -> quote.get_id().equals(quoteDTO.getValue())).collect(Collectors.toList()));
            case "author" ->
                    response.content(quoteRepository.findAll().stream().filter(quote -> quote.getAuthorSlug().equals(quoteDTO.getValue())).collect(Collectors.toList()));
            case "tag" ->
                    response.content(quoteRepository.findAll().stream().filter(quote -> quoteDTO.isMatch(Arrays.stream(quote.getTags()).toList())).collect(Collectors.toList()));
            default -> throw new FilterBadTypeException("The filter type is wrong or not supported.");
        });
    }
}
