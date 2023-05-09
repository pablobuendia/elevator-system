package org.pablo;

import org.pablo.elevator.FreightElevator;
import org.pablo.elevator.PublicElevator;

public class ElevatorSystem {

  public ElevatorSystem() {
    PublicElevator publicElevator = new PublicElevator();
    FreightElevator freightElevator = new FreightElevator();
    System.out.println("Elevator system is running");
  }

}
