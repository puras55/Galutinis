
package com.spring.bookstore.templates;

import com.spring.bookstore.Model.Book;
import com.spring.bookstore.Model.DeliveryDetails;
import java.util.Set;


public class DisplayOrders {

  public DisplayOrders() {
  }

  private Set<Book> books;
  private DeliveryDetails deliveryDetails;
  private Integer totalAmount;
  private Integer orderId;
  private String orderPlacedDate;

  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public String getOrderPlacedDate() {
    return orderPlacedDate;
  }

  public void setOrderPlacedDate(String orderPlacedDate) {
    this.orderPlacedDate = orderPlacedDate;
  }

  public Integer getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(Integer totalAmount) {
    this.totalAmount = totalAmount;
  }

  public Set<Book> getBooks() {
    return books;
  }

  public void setBooks(Set<Book> books) {
    this.books = books;
  }

  public DeliveryDetails getDeliveryDetails() {
    return deliveryDetails;
  }

  public void setDeliveryDetails(DeliveryDetails deliveryDetails) {
    this.deliveryDetails = deliveryDetails;
  }

}
