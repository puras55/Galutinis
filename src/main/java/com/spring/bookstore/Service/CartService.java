
package com.spring.bookstore.Service;

import com.spring.bookstore.Model.Book;
import com.spring.bookstore.Model.Cart;
import com.spring.bookstore.Repository.CartRepository;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CartService {

  @Autowired
  CartRepository cartRepository;

  public String updateCart(Cart c) {
    cartRepository.save(c);
    return "Cart Updated!";
  }

  public void updateBookQuantity(Integer cartId, String bookId, Integer updatedQuantity) {
    cartRepository.updateBookQuantity(cartId, bookId, updatedQuantity);
  }

}
