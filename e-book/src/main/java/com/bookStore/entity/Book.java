package com.bookStore.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity

public @Data class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotEmpty(message = "Le titre ne peut pas être vide")
	private String titre;
	@NotEmpty(message = "L'auteur ne peut pas être vide")
	private String auteur;
	@NotEmpty(message = "L'skuCode ne peut pas être vide")
	private String skuCode;
	@NotNull(message = "Le prix ne peut pas être null")
	private Integer prix;
	@NotNull(message = "La quantite ne peut pas être null")
	private Integer quantite;



}
