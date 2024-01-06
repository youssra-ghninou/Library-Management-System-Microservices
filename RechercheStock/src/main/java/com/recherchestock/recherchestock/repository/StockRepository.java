package com.recherchestock.recherchestock.repository;

import com.recherchestock.recherchestock.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock,Long> {
    List<Stock> findBySkuCodeIn(List<String> skuCode);
}
