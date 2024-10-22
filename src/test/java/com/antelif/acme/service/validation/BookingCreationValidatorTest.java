package com.antelif.acme.service.validation;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.antelif.acme.model.entity.Booking;
import com.antelif.acme.model.entity.MeetingRoom;
import com.antelif.acme.model.error.exception.BookingValidationException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Booking Creation Validator")
class BookingCreationValidatorTest {

  private Booking booking;

  @BeforeEach
  void setUp() {
    this.booking = new Booking();
    MeetingRoom meetingRoom = new MeetingRoom();
    meetingRoom.setBookings(new ArrayList<>());
    this.booking.setMeetingRoom(meetingRoom);
  }

  @Test
  @DisplayName("Fails when booking duration is less than an hour")
  void testBookingDurationIsLessThanAnHour() {

    Instant fromDate = Instant.now().plus(1, ChronoUnit.DAYS);
    Instant toDate = fromDate.plus(30, ChronoUnit.MINUTES);

    booking.setFromDate(fromDate);
    booking.setToDate(toDate);

    BookingValidationException exception =
        assertThrows(BookingValidationException.class, () -> BookingCreationValidator.validate(booking));
    assertTrue(exception.getMessage().contains("Booking duration cannot be less than an hour."));
  }

  @Test
  @DisplayName("Fails when booking duration is more than a work day")
  void testBookingDurationIsMoreThanAWorkDay() {

    Instant fromDate = Instant.now().plus(1, ChronoUnit.DAYS);
    Instant toDate = fromDate.plus(1, ChronoUnit.DAYS);

    booking.setFromDate(fromDate);
    booking.setToDate(toDate);

    BookingValidationException exception =
        assertThrows(BookingValidationException.class, () -> BookingCreationValidator.validate(booking));
    assertTrue(exception.getMessage().contains("Booking duration cannot be more than 8 hours."));
  }

  @Test
  @DisplayName("Fails when booking is in the past")
  void testBookingIsInThePast() {

    Instant fromDate = Instant.now().minus(1, ChronoUnit.DAYS);
    Instant toDate = fromDate.plus(1, ChronoUnit.HOURS);

    booking.setFromDate(fromDate);
    booking.setToDate(toDate);

    BookingValidationException exception =
        assertThrows(BookingValidationException.class, () -> BookingCreationValidator.validate(booking));
    assertTrue(exception.getMessage().contains("Booking cannot be in the past"));
  }

  @Test
  @DisplayName("Fails when booking overlaps with another one for the same meeting room")
  void testBookingsOverlap() {

    // Create a said existing booking for the meeting room
    Instant saidFromDate = Instant.now().plus(1, ChronoUnit.DAYS);
    Instant saidToDate = saidFromDate.plus(3, ChronoUnit.HOURS);
    Booking saidBooking = new Booking();
    saidBooking.setFromDate(saidFromDate);
    saidBooking.setToDate(saidToDate);

    booking.getMeetingRoom().getBookings().add(saidBooking);

    // Set the from and to dates of the booking to test to be the previous values
    Instant fromDate = saidFromDate;
    Instant toDate = saidToDate.plus(1, ChronoUnit.HOURS);
    booking.setFromDate(fromDate);
    booking.setToDate(toDate);

    BookingValidationException exception =
        assertThrows(BookingValidationException.class, () -> BookingCreationValidator.validate(booking));
    assertTrue(exception.getMessage().contains("Booking cannot overlap existing bookings."));
  }
}
