package com.pretlivres.pretlivres.service;

import com.pretlivres.pretlivres.dto.PretLineItemsDto;
import com.pretlivres.pretlivres.dto.PretRequest;
import com.pretlivres.pretlivres.model.Pret;
import com.pretlivres.pretlivres.model.PretLineItems;
import com.pretlivres.pretlivres.repository.PretRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PretService {
    private final PretRepository pretRepository;
    public List<Pret> findAll() {
        return pretRepository.findAll();
    }
    public void placePret(PretRequest pretRequest){
        Pret pret = new Pret();
        pret.setNumeroPret(UUID.randomUUID().toString());
        List<PretLineItems> preLineItems = pretRequest.getPretLineItemsDtoList()
                .stream()
                .map(this::mapToDo)
                .toList();

        pret.setPretLineItemsList(preLineItems);
        pretRepository.save(pret);
    }

    private PretLineItems mapToDo(PretLineItemsDto pretLineItemsDto) {
        PretLineItems pretLineItems = new PretLineItems();
        pretLineItems.setPrix(pretLineItemsDto.getPrix());
        pretLineItems.setQuantite(pretLineItemsDto.getQuantite());
        pretLineItems.setSkuCode(pretLineItemsDto.getSkuCode());
        return pretLineItems;
    }
}
