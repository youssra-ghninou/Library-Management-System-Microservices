package com.recherchestock.recherchestock.service;

import com.recherchestock.recherchestock.dto.StockResponse;
import com.recherchestock.recherchestock.repository.StockRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;
    @Transactional(readOnly = true)
    public List<StockResponse> isInStock(List<String> skuCode){
        return stockRepository.findBySkuCodeIn(skuCode).stream()
                .map(stock ->
                    StockResponse.builder()
                            .skuCode(stock.getSkuCode())
                            .isInStock(stock.getQuantite() > 0)
                            .build()
               ).toList();
    }
}
