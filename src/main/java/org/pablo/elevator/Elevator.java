package org.pablo.elevator;

import java.util.List;
import org.pablo.keycard.Keycard;

public interface Elevator {
  Integer STORES = 51;
  List<Integer> BASEMENTS = List.of(0);

  void moveTo(Integer nextStore);

  void addWeight(Double weight);

  void removeWeight(Double weight);

  Integer getCurrentStore();

  Double getCurrentWeight();

  boolean isAuthorized();

  boolean isWeightExceededShutoffMechanism();

  Double getWeightLimit();

  default void authorizeKeycard(Keycard keycard) {
    // do nothing
    // this method is only used in the PublicElevator class
  }
}
