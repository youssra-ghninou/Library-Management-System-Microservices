package com.pretlivres.pretlivres.service;

import com.pretlivres.pretlivres.dto.PretLineItemsDto;
import com.pretlivres.pretlivres.dto.PretRequest;
import com.pretlivres.pretlivres.dto.StockResponse;
import com.pretlivres.pretlivres.model.Pret;
import com.pretlivres.pretlivres.model.PretLineItems;
import com.pretlivres.pretlivres.repository.PretRepository;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PretService {
    private final PretRepository pretRepository;
    private final WebClient.Builder webClientBuilder;
    public List<Pret> findAll() {
        return pretRepository.findAll();
    }
    public void placePret(PretRequest pretRequest, String token){
        Pret pret = new Pret();
        pret.setNumeroPret(UUID.randomUUID().toString());
        List<PretLineItems> preLineItems = pretRequest.getPretLineItemsDtoList()
                .stream()
                .map(this::mapToDo)
                .toList();

        pret.setPretLineItemsList(preLineItems);

        List<String> skuCodes = pret.getPretLineItemsList().stream()
                .map(PretLineItems::getSkuCode)
                .toList();

        //call Stock Service, and place pret if book is in stock
        StockResponse[] stockResponseArray =  webClientBuilder.build().get()
                .uri("http://bookStore/api/v1/books/recherche-stock",
                        uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .retrieve()
                                .bodyToMono(StockResponse[].class)
                                        .block();


        boolean allBooksInStock =  Arrays.stream(stockResponseArray).allMatch(StockResponse::isInStock);

        if (allBooksInStock){
            pretRepository.save(pret);
        }else {
            throw new IllegalArgumentException("Le livre n'est pas dans le stock");
        }
    }

    private PretLineItems mapToDo(PretLineItemsDto pretLineItemsDto) {
        PretLineItems pretLineItems = new PretLineItems();
        pretLineItems.setPrix(pretLineItemsDto.getPrix());
        pretLineItems.setQuantite(pretLineItemsDto.getQuantite());
        pretLineItems.setSkuCode(pretLineItemsDto.getSkuCode());
        return pretLineItems;
    }
}
