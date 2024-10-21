package com.antelif.acme.model.error.exception;

import com.antelif.acme.model.error.AcmeError;

public class CreationException extends AcmeException {

  public CreationException(String... args) {
    super(AcmeError.CREATION_ERROR, args);
  }
}
