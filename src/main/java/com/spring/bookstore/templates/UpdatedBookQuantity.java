
package com.spring.bookstore.templates;

import java.io.Serializable;


public class UpdatedBookQuantity implements Serializable {

  private String bookId;
  private Integer quantity;

  public String getBookId() {
    return bookId;
  }

  public void setBookId(String bookId) {
    this.bookId = bookId;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

}
