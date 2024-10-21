package com.antelif.acme.service.validation;

import com.antelif.acme.model.entity.Booking;
import com.antelif.acme.model.error.exception.BookingValidationException;
import java.time.Instant;

/** Performs checks to new booking. */
public final class BookingValidator {

  private BookingValidator() {}

  /**
   * Checks new booking for errors.
   *
   * @param booking the new booking to be persisted.
   */
  public static void validate(Booking booking) {
    bookingDurationIsMoreThanAWorkDay(booking);
    bookingDurationIsLessThanAnHour(booking);
    bookingIsInThePast(booking);
    bookingsOverlap(booking);
  }

  private static void bookingDurationIsLessThanAnHour(Booking booking) {

    if (booking.meetingDuration().toHoursPart() < 1) {
      throw new BookingValidationException("Booking duration cannot be less than an hour.");
    }
  }

  private static void bookingDurationIsMoreThanAWorkDay(Booking booking) {

    if (booking.meetingDuration().toHours() > 8) {
      throw new BookingValidationException("Booking duration cannot be more than 8 hours.");
    }
  }

  private static void bookingIsInThePast(Booking booking) {
    if (booking.getFromDate().isBefore(Instant.now())) {
      throw new BookingValidationException("Booking cannot be in the past.");
    }
  }

  private static void bookingsOverlap(Booking booking) {
    if (booking.getMeetingRoom().getBookings().stream()
        .anyMatch(persistedBooking -> persistedBooking.overlaps(booking))) {
      throw new BookingValidationException("Booking cannot overlap existing bookings.");
    }
  }
}
