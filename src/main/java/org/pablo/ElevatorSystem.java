package org.pablo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.pablo.elevator.Elevator;
import org.pablo.elevator.FreightElevator;
import org.pablo.elevator.PublicElevator;
import org.pablo.keycard.Keycard;

@Slf4j
public class ElevatorSystem {

  private final HashMap<Integer, Keycard> keycards = new HashMap<>();

  private static final ElevatorSystem INSTANCE = new ElevatorSystem();

  private ElevatorSystem() {
  }

  public static ElevatorSystem getInstance() {
    return INSTANCE;
  }

  public void start() {
    val elevators = new ArrayList<Elevator>();
    elevators.add(new PublicElevator());
    elevators.add(new FreightElevator());
    log.info("Elevator system is running");

    val scanner = new Scanner(System.in);

    log.info("Welcome to the elevator system");
    int type;
    do {
      log.info("Please select the type of elevator you want to use");
      log.info("1. Public Elevator");
      log.info("2. Freight Elevator");
      log.info("3. Exit");

      type = scanner.nextInt();

      manageElevator(elevators.get(type - 1), scanner);
    } while (type != 3);
    scanner.close();
    log.info("Thank you for using the elevator system");
  }

  private void manageElevator(Elevator elevator,
      final Scanner scanner) {
    int option;
    do {
      log.info("Choose a new option");
      log.info("1. Move to a store");
      log.info("2. Add weight");
      log.info("3. Remove weight");
      log.info("4. Create a new keycard");
      log.info("5. Authorize keycard");
      log.info("9. Exit");

      option = scanner.nextInt();

      double weight;
      switch (option) {
        case 1:
          log.info("Enter the store you want to move to");
          int store = scanner.nextInt();
          elevator.moveTo(store);
          break;
        case 2:
          log.info("Enter the weight you want to add");
          weight = scanner.nextDouble();
          elevator.addWeight(weight);
          break;
        case 3:
          log.info("Enter the weight you want to remove");
          weight = scanner.nextDouble();
          elevator.removeWeight(weight);
          break;
        case 4:
          log.info("Creating a new keycard...");
          val keycard = new Keycard();
          keycards.put(keycard.getKeycardId(), keycard);
          break;
        case 5:
          log.info("Enter the keycard id");
          int keycardId = scanner.nextInt();
          if (keycards.containsKey(keycardId)) {
            log.info("Keycard {} authorized", keycardId);
            elevator.authorizeKeycard(keycards.get(keycardId));
          } else {
            log.info("Keycard {} not found", keycardId);
          }
          break;
        case 9:
          log.info("Exiting...");
          break;
        default:
          log.info("Invalid option");
          break;
      }
    } while (option != 9);
  }

}
