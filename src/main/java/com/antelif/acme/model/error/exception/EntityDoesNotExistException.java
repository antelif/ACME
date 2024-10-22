package com.antelif.acme.model.error.exception;

import com.antelif.acme.model.error.AcmeError;

public class EntityDoesNotExistException extends AcmeException {

  public EntityDoesNotExistException(String... args) {
    super(AcmeError.ENTITY_DOES_NOT_EXIST_ERROR, args);
  }
}
