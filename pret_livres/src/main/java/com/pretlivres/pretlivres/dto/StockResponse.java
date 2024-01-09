package com.pretlivres.pretlivres.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockResponse {
    private Integer Id;
    private String skuCode;
    private Integer quantite;
    private boolean isInStock;
}
