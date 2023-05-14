package org.pablo;

import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.pablo.elevator.FreightElevator;
import org.pablo.elevator.PublicElevator;

@Slf4j
public class ElevatorSystem {

  private final static ElevatorSystem INSTANCE = new ElevatorSystem();

  private ElevatorSystem() {
  }

  public static ElevatorSystem getInstance() {
    return INSTANCE;
  }

  public void start() {
    PublicElevator publicElevator = new PublicElevator();
    FreightElevator freightElevator = new FreightElevator();
    System.out.println("Elevator system is running");


    val scanner = new Scanner(System.in);

    log.info("Welcome to the elevator system");
    log.info("Please select the type of elevator you want to use");
    log.info("1. Public Elevator");
    log.info("2. Freight Elevator");
    log.info("3. Exit");

    int type = scanner.nextInt();
    int option = 0;
    do {
      log.info("Choose a new option");
      log.info("1. Move to a store");
      log.info("2. Add weight");
      log.info("3. Remove weight");
      log.info("4. Exit");

      option = scanner.nextInt();

      switch (option) {
        case 1:
          log.info("Enter the store you want to move to");
          int store = scanner.nextInt();
          if (type == 1) {
            publicElevator.moveTo(store);
          } else {
            freightElevator.moveTo(store);
          }
          break;
        case 2:
          log.info("Enter the weight you want to add");
          double weight = scanner.nextDouble();
          if (type == 1) {
            publicElevator.addWeight(weight);
          } else {
            freightElevator.addWeight(weight);
          }
          break;
        case 3:
          log.info("Enter the weight you want to remove");
          weight = scanner.nextDouble();
          if (type == 1) {
            publicElevator.removeWeight(weight);
          } else {
            freightElevator.removeWeight(weight);
          }
          break;
        case 4:
          log.info("Exiting...");
          break;
        default:
          log.info("Invalid option");
          break;
      }
      log.info("Choose a new option");

    } while (option != 4);

    scanner.close();

    log.info("Thank you for using the elevator system");
  }

}
