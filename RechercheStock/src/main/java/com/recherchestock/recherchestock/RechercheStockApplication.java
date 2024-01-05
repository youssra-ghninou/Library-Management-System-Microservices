package com.recherchestock.recherchestock;

import com.recherchestock.recherchestock.model.Stock;
import com.recherchestock.recherchestock.repository.StockRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RechercheStockApplication {

	public static void main(String[] args) {
		SpringApplication.run(RechercheStockApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(StockRepository stockRepository){
		return args -> {
			Stock stock = new Stock();
			stock.setSkuCode("Spring Boot 13");
			stock.setQuantite(100);

			Stock stock1 = new Stock();
			stock1.setSkuCode("Spring Boot 12");
			stock1.setQuantite(0);

			stockRepository.save(stock);
			stockRepository.save(stock1);
		};
	}

}
