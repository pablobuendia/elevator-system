package org.pablo.keycard;

import java.security.SecureRandom;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
public class Keycard {

  private final Integer keycardId;

  public Keycard() {
    val secureRandom = new SecureRandom();
    keycardId = secureRandom.nextInt(1000);
    log.info("Keycard {} created, please remember your keycard id", keycardId);
  }

  public Integer getKeycardId() {
    return keycardId;
  }
}
