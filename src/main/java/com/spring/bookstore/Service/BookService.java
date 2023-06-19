
package com.spring.bookstore.Service;

import com.spring.bookstore.Model.Book;
import com.spring.bookstore.Repository.BookRepository;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookService {

  @Autowired
  BookRepository bookRepository;

  public Set<Book> fetchBookByBookId(Integer cartId, String bookId) {
    return bookRepository.fetchBookByBookId(cartId, bookId);
  }

  public void deleteBookById(Integer id) {
    bookRepository.deleteById(id);
  }

  public boolean boookExistsInCart(Integer cartId, String bookId) {
    int count = bookRepository.bookExistsInCart(cartId, bookId);
    if (count > 0) {
      return true;
    }
    return false;
  }

  public Integer getBookQuantity(Integer cartId, String bookId) {
    return bookRepository.getBookQuantity(cartId, bookId);
  }
}
