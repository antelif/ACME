package com.antelif.acme.service.validation;

import com.antelif.acme.model.entity.Booking;
import com.antelif.acme.model.error.exception.BookingValidationException;
import java.time.Instant;

/** Performs checks to new booking. */
public final class BookingDeletionValidator {

  private BookingDeletionValidator() {}

  /**
   * Checks new booking for errors.
   *
   * @param booking the new booking to be persisted.
   */
  public static void validate(Booking booking) {
    bookingIsInThePast(booking);
  }

  private static void bookingIsInThePast(Booking booking) {
    if (Instant.now().isAfter(booking.getFromDate())) {
      throw new BookingValidationException("Cannot delete past booking.");
    }
  }
}
