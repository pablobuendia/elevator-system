package org.pablo.exceptions;

public class UnauthorizedKeycardException extends RuntimeException {

  public UnauthorizedKeycardException() {
    super("Unauthorized: keycard system invalid or inexistent");
  }
}
