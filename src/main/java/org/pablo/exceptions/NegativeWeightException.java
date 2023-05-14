package org.pablo.exceptions;

public class NegativeWeightException extends RuntimeException {

  public NegativeWeightException() {
    super("Invalid input: Weight to remove is more than weight load or weight can be negative");
  }
}
