package com.pretlivres.pretlivres.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Entity
@Table(name="t_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroPret;
    @OneToMany(cascade = CascadeType.ALL)
    private List<PretLineItems> pretLineItemsList;
}
