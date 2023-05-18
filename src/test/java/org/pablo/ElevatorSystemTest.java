package org.pablo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pablo.elevator.FreightElevator;
import org.pablo.elevator.PublicElevator;
import org.pablo.keycard.Keycard;

@ExtendWith(MockitoExtension.class)
class ElevatorSystemTest {

  @Mock
  private PublicElevator publicElevator;
  @Mock
  private FreightElevator freightElevator;
  @Mock
  private Scanner scanner;
  @Mock
  private Map<Integer, Keycard> keycards;

  @InjectMocks
  private ElevatorSystem elevatorSystem;

  @Test
  void manageElevator() {
    when(scanner.nextInt()).thenReturn(1, 1, 2, 9);
    elevatorSystem.manageElevator(publicElevator, scanner);
    verify(publicElevator).moveTo(1);
  }

  @Test
  void manageElevatorFreight() {
    when(scanner.nextInt()).thenReturn(1, 1, 2, 9);
    elevatorSystem.manageElevator(freightElevator, scanner);
    verify(freightElevator).moveTo(1);
  }

  @Test
  void manageElevatorInvalidOptionBefore() {
    when(scanner.nextInt()).thenReturn(10, 1, 1, 2, 9);
    elevatorSystem.manageElevator(freightElevator, scanner);
    verify(freightElevator).moveTo(1);
  }

  @Test
  void manageElevatorInvalidOptionAfter() {
    when(scanner.nextInt()).thenReturn(1, 1, 2, 9, 10);
    elevatorSystem.manageElevator(freightElevator, scanner);
    verify(freightElevator).moveTo(1);
  }

  @Test
  void manageElevatorInvalidOptionBetween() {
    when(scanner.nextInt()).thenReturn(1, 1, 2, 10, 9);
    elevatorSystem.manageElevator(freightElevator, scanner);
    verify(freightElevator).moveTo(1);
  }

  @Test
  void authorizeElevatorKeycard() {
    when(keycards.get(1)).thenReturn(new Keycard());
    when(scanner.nextInt()).thenReturn(1);
    when(keycards.containsKey(any())).thenReturn(true);

    elevatorSystem.authorizeElevatorKeycard(publicElevator, scanner);

    verify(publicElevator, times(1)).authorizeKeycard(any());
  }

  @Test
  void authorizeElevatorKeycardNotFound() {
    when(scanner.nextInt()).thenReturn(1);
    when(keycards.containsKey(any())).thenReturn(false);

    elevatorSystem.authorizeElevatorKeycard(publicElevator, scanner);

    verify(publicElevator, times(0)).authorizeKeycard(any());
  }

  @Test
  void createKeycard() {
    elevatorSystem.createKeycard();
    verify(keycards, times(1)).put(anyInt(), any());
  }

  @Test
  void removeWeightFromElevator() {
    elevatorSystem.removeWeightFromElevator(freightElevator, scanner);
    verify(freightElevator).removeWeight(anyDouble());
  }

  @Test
  void addWeightToElevator() {
    elevatorSystem.addWeightToElevator(freightElevator, scanner);
    verify(freightElevator).addWeight(anyDouble());
  }

  @Test
  void moveElevatorTo() {
    elevatorSystem.moveElevatorTo(freightElevator, scanner);
    verify(freightElevator).moveTo(anyInt());
  }

  @Test
  void moveElevatorToThrowException() {
    when(scanner.nextInt()).thenReturn(1, 1, 2, 9);
    doThrow(new RuntimeException()).when(freightElevator).moveTo(anyInt());
    elevatorSystem.moveElevatorTo(freightElevator, scanner);
    verify(freightElevator).moveTo(anyInt());
  }
}