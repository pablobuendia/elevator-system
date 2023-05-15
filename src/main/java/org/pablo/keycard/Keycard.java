package org.pablo.keycard;

import java.security.SecureRandom;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Keycard {

  private final SecureRandom secureRandom = new SecureRandom();
  private final Integer keycardId;

  public Keycard() {
    keycardId = secureRandom.nextInt(1000);
    log.info("Keycard {} created", keycardId);
  }

  public Integer getKeycardId() {
    return keycardId;
  }
}
