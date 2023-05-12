package org.pablo.elevator;

import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.pablo.exceptions.NegativeWeightException;
import org.pablo.exceptions.ShutoffElevatorMechanismException;
import org.pablo.exceptions.StoreNotFoundException;
import org.pablo.exceptions.UnauthorizedKeycardException;

@Getter
@Slf4j
public class PublicElevator {

  public static final Double WEIGHT_LIMIT = 1000.0;

  private static final Integer STORES = 51;
  private static final Integer BASEMENTS = 1;
  private static final List<Integer> protectedStores = List.of(0,50);

  private Integer currentStore = 1;
  private Double currentWeight = 0.0;
  private boolean authorized = false;
  private boolean weightExceededShutoffMechanism = false;

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

    log.info("Moving to store {}...", nextStore);
    currentStore = nextStore;
    log.info("Store {} reached", currentStore);
  }

  public void addWeight(Double weight) {
    currentWeight += weight;
    if (currentWeight >= WEIGHT_LIMIT) {
      weightExceededShutoffMechanism = true;
      log.warn("Weight Limit Exceeded!");
      log.warn("Shutting off elevator movement until weight limit is normal again");
    }
  }

  public void removeWeight(Double weight) {
    if (currentWeight - weight < 0) {
      throw new NegativeWeightException();
    }
    currentWeight -= weight;
    if (weightExceededShutoffMechanism && currentWeight < WEIGHT_LIMIT) {
      weightExceededShutoffMechanism = false;
      log.info("Weight is normal again. Elevator can move normally.");
    }
  }
}
