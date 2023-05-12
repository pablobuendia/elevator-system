package org.pablo.exceptions;

public class ShutoffElevatorMechanismException extends RuntimeException {

  public ShutoffElevatorMechanismException() {
    super("Forbidden: Cannot move while weight limit is exceeded");
  }

}
