package com.bookStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookStore.entity.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer>  {
    List<Book> findBySkuCodeIn(List<String> skuCode);

    Optional<Book> findBySkuCode(String skuCode);
}