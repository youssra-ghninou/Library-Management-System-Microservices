package com.pretlivres.pretlivres.controller;

import com.pretlivres.pretlivres.dto.PretRequest;
import com.pretlivres.pretlivres.model.Pret;
import com.pretlivres.pretlivres.service.PretService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/prets")
@RequiredArgsConstructor
public class PretController {
    private final PretService pretService;
    @GetMapping
    public List<Pret> getAllPret(){
        return pretService.findAll();
    }
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String placePret(@RequestBody PretRequest pretRequest){
        pretService.placePret(pretRequest);
        return "Prêt créé avec succès";
    }
}
