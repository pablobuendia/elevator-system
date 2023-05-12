package org.pablo.elevator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pablo.exceptions.ShutoffElevatorMechanismException;
import org.pablo.exceptions.StoreNotFoundException;

@ExtendWith(MockitoExtension.class)
class PublicElevatorTest {

  @InjectMocks private PublicElevator publicElevator;

  @Test
  void moveTo() {
    publicElevator.moveTo(1);
    assertEquals(1, publicElevator.getCurrentStore());
  }

  @Test
  void moveToStoreNotFoundException() {
    assertThrows(StoreNotFoundException.class, () -> publicElevator.moveTo(90));
  }

  @Test
  void moveToShutoffElevatorMechanismException() {
    publicElevator.addWeight(50000.0);
    assertThrows(ShutoffElevatorMechanismException.class, () -> publicElevator.moveTo(1));
  }

  @Test
  void addWeight() {
    publicElevator.addWeight(100.0);
    assertEquals(100.0, publicElevator.getCurrentWeight());
  }

  @Test
  void removeWeightWhenItsZero() {
    assertThrows(RuntimeException.class, () -> publicElevator.removeWeight(100.0));
  }
}