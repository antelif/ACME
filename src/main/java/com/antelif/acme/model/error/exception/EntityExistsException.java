package com.antelif.acme.model.error.exception;

import com.antelif.acme.model.error.AcmeError;

public class EntityExistsException extends AcmeException {

  public EntityExistsException(String... args) {
    super(AcmeError.ENTITY_EXISTS_ERROR, args);
  }
}
