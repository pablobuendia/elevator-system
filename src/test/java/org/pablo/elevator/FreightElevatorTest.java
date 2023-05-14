package org.pablo.elevator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.pablo.exceptions.NegativeWeightException;
import org.pablo.exceptions.StoreNotFoundException;

class FreightElevatorTest {

  private FreightElevator freightElevator = new FreightElevator();

  @Test
  void moveTo() {
    freightElevator.moveTo(1);
    assertEquals(1, freightElevator.getCurrentStore());
  }

  @Test
  void moveToInvalidStore() {
    assertThrows(StoreNotFoundException.class, () -> freightElevator.moveTo(52));
  }

  @Test
  void moveToNegativeStore() {
    assertThrows(StoreNotFoundException.class, () -> freightElevator.moveTo(-1));
  }

  @Test
  void addWeight() {
    freightElevator.addWeight(100.0);
    assertEquals(100.0, freightElevator.getCurrentWeight());
  }

  @Test
  void addWeightExceedsLimit() {
    freightElevator.addWeight(3000.0);
    assertTrue(freightElevator.isWeightExceededShutoffMechanism());
  }

  @Test
  void addWeightExceedsLimitAndThenNormalizes() {
    freightElevator.addWeight(3000.0);
    assertTrue(freightElevator.isWeightExceededShutoffMechanism());
    freightElevator.removeWeight(100.0);
    assertFalse(freightElevator.isWeightExceededShutoffMechanism());
  }

  @Test
  void addNegativeWeight() {
    assertThrows(NegativeWeightException.class, () -> freightElevator.addWeight(-100.0));
  }

  @Test
  void removeNegativeWeight() {
    assertThrows(NegativeWeightException.class, () -> freightElevator.removeWeight(-100.0));
  }

  @Test
  void removeWeightExceedsCurrentWeight() {
    assertThrows(NegativeWeightException.class, () -> freightElevator.removeWeight(100.0));
  }

  @Test
  void removeWeightExceedsCurrentWeightAndThenNormalizesAndThenExceedsAgain() {
    freightElevator.addWeight(100.0);
    assertEquals(100.0, freightElevator.getCurrentWeight());
    freightElevator.removeWeight(100.0);
    assertEquals(0.0, freightElevator.getCurrentWeight());
    freightElevator.addWeight(3000.0);
    assertTrue(freightElevator.isWeightExceededShutoffMechanism());
  }

  @Test
  void removeWeight() {
    freightElevator.addWeight(100.0);
    assertEquals(100.0, freightElevator.getCurrentWeight());
    freightElevator.removeWeight(100.0);
    assertEquals(0.0, freightElevator.getCurrentWeight());
  }

  @Test
  void removeWeightExceedsLimit() {
    freightElevator.addWeight(3000.0);
    assertTrue(freightElevator.isWeightExceededShutoffMechanism());
    freightElevator.removeWeight(3000.0);
    assertFalse(freightElevator.isWeightExceededShutoffMechanism());
  }
}