package org.pablo.elevator;

public interface Elevator {

  Integer STORES = 50;
  Integer BASEMENTS = 1;

  void moveUp(final Integer stores);

  void moveDown(final Integer stores);

  void addWeight(final Double weight);

  void removeWeight(final Double weight);
}
