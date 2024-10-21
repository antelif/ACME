package com.antelif.acme.model.error.exception;

import com.antelif.acme.model.error.AcmeError;
import lombok.Getter;

/** Wrapper exception. */
@Getter
public class AcmeException extends RuntimeException {
  private final AcmeError error;
  private final String message;

  public AcmeException(AcmeError error, String... args) {
    super(String.format(error.getDescription(), String.join(" ", args)));
    this.error = error;
    this.message = String.format(error.getDescription(), String.join(" ", args));
  }
}
