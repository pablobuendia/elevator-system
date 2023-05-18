package org.pablo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.pablo.elevator.Elevator;
import org.pablo.elevator.FreightElevator;
import org.pablo.elevator.PublicElevator;
import org.pablo.keycard.Keycard;

@Slf4j
public class ElevatorSystem {

  private final Map<Integer, Keycard> keycards;
  private final List<Elevator> elevators;
  private final Scanner scanner = new Scanner(System.in);

  public ElevatorSystem(final Elevator publicElevator, final Elevator freightElevator,
      final Map<Integer, Keycard> keycards) {
    elevators = new ArrayList<>();
    elevators.add(publicElevator);
    elevators.add(freightElevator);
    this.keycards = keycards;
  }

  public ElevatorSystem() {
    elevators = new ArrayList<>();
    elevators.add(new PublicElevator());
    elevators.add(new FreightElevator());
    this.keycards = new HashMap<>();
  }

  public void start() {

    log.info("Elevator system is running");

    log.info("Welcome to the elevator system");
    int type;
    do {
      log.info("Please select the type of elevator you want to use");
      log.info("1. Public Elevator");
      log.info("2. Freight Elevator");
      log.info("3. Exit");

      type = scanner.nextInt();

      log.info("You selected option {}", type);
      if (type == 3)
        break;
      manageElevator(elevators.get(type - 1), scanner);
    } while (type != 3);
    scanner.close();
    log.info("Thank you for using the elevator system");
  }

  void manageElevator(final Elevator elevator, final Scanner scanner) {
    int option;
    do {
      showOptions();
      option = scanner.nextInt();

      switch (option) {
        case 1:
          moveElevatorTo(elevator, scanner);
          break;
        case 2:
          addWeightToElevator(elevator, scanner);
          break;
        case 3:
          removeWeightFromElevator(elevator, scanner);
          break;
        case 4:
          createKeycard();
          break;
        case 5:
          authorizeElevatorKeycard(elevator, scanner);
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

  void authorizeElevatorKeycard(final Elevator elevator, final Scanner scanner) {
    log.info("Enter the keycard id");
    int keycardId = scanner.nextInt();
    if (keycards.containsKey(keycardId)) {
      log.info("Keycard {} authorized", keycardId);
      elevator.authorizeKeycard(keycards.get(keycardId));
    } else {
      log.info("Keycard {} not found", keycardId);
    }
  }

  void createKeycard() {
    log.info("Creating a new keycard...");
    val keycard = new Keycard();
    keycards.put(keycard.getKeycardId(), keycard);
  }

  void removeWeightFromElevator(Elevator elevator, Scanner scanner) {
    double weight;
    log.info("Enter the weight you want to remove");
    weight = scanner.nextDouble();
    elevator.removeWeight(weight);
  }

  void addWeightToElevator(Elevator elevator, Scanner scanner) {
    double weight;
    log.info("Enter the weight you want to add");
    weight = scanner.nextDouble();
    elevator.addWeight(weight);
  }

  void moveElevatorTo(final Elevator elevator, final Scanner scanner) {
    log.info("Enter the store you want to move to");
    int store = scanner.nextInt();
    try {
      elevator.moveTo(store);
    } catch (Exception e) {
      log.error("Error moving to store {}. Cause was: {}", store, e.getMessage());
    }
  }

  void showOptions() {
    log.info("Choose a new option");
    log.info("1. Move to a store");
    log.info("2. Add weight");
    log.info("3. Remove weight");
    log.info("4. Create a new keycard");
    log.info("5. Authorize keycard");
    log.info("9. Exit");
  }
}
