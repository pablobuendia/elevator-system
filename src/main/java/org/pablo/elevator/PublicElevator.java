package org.pablo.elevator;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pablo.exceptions.NegativeWeightException;
import org.pablo.exceptions.ShutoffElevatorMechanismException;
import org.pablo.exceptions.StoreNotFoundException;
import org.pablo.exceptions.UnauthorizedKeycardException;
import org.pablo.keycard.Keycard;

@Getter
@Slf4j
@NoArgsConstructor
public class PublicElevator implements Elevator {

  public static final Double WEIGHT_LIMIT = 1000.0;

  private static final List<Integer> protectedStores = List.of(0,50);

  private Integer currentStore = 1;
  private Double currentWeight = 0.0;
  private boolean authorized = false;
  private boolean weightExceededShutoffMechanism = false;

  @Override
  public void moveTo(final Integer nextStore) {
    if (nextStore >= STORES || nextStore < 0) {
      throw new StoreNotFoundException();
    }
    if (protectedStores.contains(nextStore) && !authorized) {
      throw new UnauthorizedKeycardException();
    }
    if (weightExceededShutoffMechanism) {
      throw new ShutoffElevatorMechanismException();
    }
    if (currentStore.equals(nextStore)) {
      log.info("Already in store {}", nextStore);
      return;
    }

    log.info("\n\nMoving to store {}...", nextStore);
    currentStore = nextStore;
    log.info("Store {} reached\n\n", currentStore);
    if (Elevator.BASEMENTS.contains(currentStore)) {
      log.info("Elevator is in basement");
    }

    authorized = false;
  }

  @Override
  public void addWeight(Double weight) {
    if (weight < 0) {
      throw new NegativeWeightException();
    }
    currentWeight += weight;
    log.info("Current weight: {}", currentWeight);
    if (currentWeight >= WEIGHT_LIMIT) {
      weightExceededShutoffMechanism = true;
      log.warn("Weight Limit Exceeded!");
      log.warn("Shutting off elevator movement until weight limit is normal again");
    }
  }

  @Override
  public void removeWeight(Double weight) {
    if (weight < 0) {
      throw new NegativeWeightException();
    }
    if (currentWeight - weight < 0) {
      throw new NegativeWeightException();
    }
    currentWeight -= weight;
    log.info("Current weight: {}", currentWeight);
    if (weightExceededShutoffMechanism && currentWeight < WEIGHT_LIMIT) {
      weightExceededShutoffMechanism = false;
      log.info("Weight is normal again. Elevator can move normally.");
    }
  }

  @Override
  public Double getWeightLimit() {
    return WEIGHT_LIMIT;
  }


  @Override
  public void authorizeKeycard(Keycard keycard) {
    if (keycard != null) {
      authorized = true;
    }
    else {
      log.warn("Invalid keycard");
      authorized = false;
    }
  }
}
