package com.recherchestock.recherchestock.controller;

import com.recherchestock.recherchestock.dto.StockResponse;
import com.recherchestock.recherchestock.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recherche-stock")
@RequiredArgsConstructor
@Slf4j
public class StockController {

    private final StockService stockService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StockResponse> isInStock(@RequestParam List<String> skuCode){
        log.info("Received inventory check request for skuCode: {}", skuCode);
        return stockService.isInStock(skuCode);
    }
}
