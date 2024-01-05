package com.pretlivres.pretlivres.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name="t_prets_line_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PretLineItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String  skuCode;
    private BigDecimal prix;
    private Integer quantite;
}
