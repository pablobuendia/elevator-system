package org.pablo.elevator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PublicElevatorTest {

  @InjectMocks private PublicElevator publicElevator;

  @Test
  void moveTo() {
    publicElevator.moveTo(1);
    assertEquals(1, publicElevator.getCurrentStore());
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