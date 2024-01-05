package com.pretlivres.pretlivres.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PretLineItemsDto {
    private Long id;
    private String  skuCode;
    private BigDecimal prix;
    private Integer quantite;
}
