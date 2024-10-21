package com.antelif.acme.model.error;

import lombok.Getter;

@Getter
public enum AcmeError {
  UNKNOWN_ERROR(1, "An unknown error occurred."),
  CREATION_ERROR(2, "An error during creation of new object occurred. Object %s"),
  ENTITY_EXISTS_ERROR(3, "Object already exists in database. Object %s");

  private final int code;
  private final String description;

  AcmeError(int code, String description) {
    this.code = code;
    this.description = description;
  }
}
