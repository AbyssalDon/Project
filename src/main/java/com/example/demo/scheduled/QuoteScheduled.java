package com.example.demo.scheduled;

import com.example.demo.repository.QuoteRepository;
import com.example.demo.service.QuoteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class QuoteScheduled {
    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private QuoteService quoteService;

    @Value("${timer.maxrecords}")
    private int records;

    @Scheduled(fixedDelay = 60000)
    @Transactional
    public void saveQuote() {
        if (quoteRepository.count() < this.records)
            quoteRepository.save(quoteService.receiveQuote());
    }
}
