package com.bookStore.service;

import com.bookStore.dto.StockResponse;
import com.bookStore.entity.Book;
import com.bookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	public Optional<Book> findById(Integer id) {
		return bookRepository.findById(id);
	}

	public Book save(Book book) {
		return bookRepository.save(book);
	}

	public void deleteById(Integer id) {
		bookRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public List<StockResponse> isInStock(List<String> skuCode){
		List<Book> books = bookRepository.findBySkuCodeIn(skuCode);

		// Check if the number of books found matches the number of SKU codes provided
		if (books.size() != skuCode.size()) {
			// Find which skuCodes were not found
			List<String> foundSkuCodes = books.stream().map(Book::getSkuCode).toList();
			String notFoundSkuCodes = skuCode.stream()
					.filter(skuCodeSingle -> !foundSkuCodes.contains(skuCode))
					.collect(Collectors.joining(", "));

			throw new IllegalArgumentException("Invalid skuCode(s) provided: " + notFoundSkuCodes);
		}

		return books.stream()
				.map(stock ->
						StockResponse.builder()
								.skuCode(stock.getSkuCode())
								.isInStock(stock.getQuantite() > 0)
								.build()
				).toList();
	}

}
