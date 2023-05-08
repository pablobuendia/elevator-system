package org.pablo;

import org.pablo.elevator.Elevator;
import org.pablo.elevator.FreightElevator;
import org.pablo.elevator.PublicElevator;

public class ElevatorSystem {

  public ElevatorSystem() {
    Elevator publicElevator = new PublicElevator();
    Elevator freightElevator = new FreightElevator();
    System.out.println("Elevator system is running");
  }

}
