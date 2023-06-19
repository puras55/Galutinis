
package com.spring.bookstore.templates;

import java.io.Serializable;


public class UniqueBookId implements Serializable {

  public UniqueBookId() {
  }
  private String uniqueBookId;

  public String getUniqueBookId() {
    return uniqueBookId;
  }

  public void setUniqueBookId(String uniqueBookId) {
    this.uniqueBookId = uniqueBookId;
  }

}
