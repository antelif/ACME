package com.antelif.acme.model.error.exception;

import com.antelif.acme.model.error.AcmeError;
import com.antelif.acme.model.error.exception.AcmeException;

public class BookingValidationException extends AcmeException {

  public BookingValidationException(String... args) {
    super(AcmeError.BOOKING_INVALID_DURATION_ERROR, args);
  }
}
