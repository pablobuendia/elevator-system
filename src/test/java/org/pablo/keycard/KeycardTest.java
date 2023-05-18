package org.pablo.keycard;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class KeycardTest {

  @Test
  void getKeycardIdNotNull() {
    Keycard keycard = new Keycard();
    assertNotNull(keycard.getKeycardId());
  }
}