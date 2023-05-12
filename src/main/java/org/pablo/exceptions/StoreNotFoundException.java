package org.pablo.exceptions;

public class StoreNotFoundException extends RuntimeException{

  public StoreNotFoundException() {
    super("Invalid input: store does not exist");
  }
}
