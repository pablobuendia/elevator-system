package org.pablo.elevator;

import lombok.RequiredArgsConstructor;

public class FreightElevator {

  public static final Integer WEIGHT_LIMIT = 3000;
  public Double currentWeight = 0.0;
  public Integer currentStore = 0;

  public void moveUp(Integer stores) {
    if (currentWeight > WEIGHT_LIMIT) {
      System.out.println("Weight limit exceeded!!");
    } else {
      currentStore += stores;
    }
  }

  public void moveDown(Integer stores) {
    if (currentWeight > WEIGHT_LIMIT) {
      System.out.println("Weight limit exceeded!!");
    } else {
      currentStore -= stores;
    }
  }

  public void addWeight(Double weight) {
    currentWeight += weight;
  }

  public void removeWeight(Double weight) {
    currentWeight -= weight;
  }
}
