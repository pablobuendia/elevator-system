package org.pablo.elevator;

public interface Elevator {

  void moveTo(Integer nextStore);

  void addWeight(Double weight);

  void removeWeight(Double weight);

  Integer getCurrentStore();

  Double getCurrentWeight();

  boolean isAuthorized();

  boolean isWeightExceededShutoffMechanism();

  Double getWeightLimit();
}
