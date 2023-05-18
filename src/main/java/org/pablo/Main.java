package org.pablo;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
public class Main {

  public static void main(String[] args) {
    val elevatorSystem = new ElevatorSystem();
    elevatorSystem.start();
  }
}