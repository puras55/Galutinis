
package com.spring.bookstore.Repository;

import com.spring.bookstore.Model.Book;
import com.spring.bookstore.Model.Cart;
import java.util.Set;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public interface CartRepository extends CrudRepository<Cart, Integer> {


  @Transactional
  @Modifying
  @Query(value = "update book b set quantity = ?3 where b.id in (select cb.book_id from cart_books cb where cb.cart_id = ?1) and (b.book_id=?2)", nativeQuery = true)
  void updateBookQuantity(Integer cartId, String bookId, Integer updatedQuantity);

}
