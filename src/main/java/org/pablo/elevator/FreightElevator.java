package org.pablo.elevator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pablo.exceptions.NegativeWeightException;
import org.pablo.exceptions.StoreNotFoundException;

@Slf4j
@Getter
@RequiredArgsConstructor
public class FreightElevator implements Elevator {

  public static final Double WEIGHT_LIMIT = 3000.0;
  private Double currentWeight = 0.0;
  private Integer currentStore = 1;
  private boolean weightExceededShutoffMechanism = false;

  @Override
  public void moveTo(final Integer store) {
    if (store < 0 || store > STORES) {
      throw new StoreNotFoundException();
    }
    log.info("Moving to store {}...", store);
    currentStore = store;
    log.info("Store {} reached", currentStore);
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
  public boolean isAuthorized() {
    return true;
  }

  @Override
  public Double getWeightLimit() {
    return Double.valueOf(WEIGHT_LIMIT);
  }
}
