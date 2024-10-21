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

@DisplayName("Booking Deletion Validator")
class BookingDeletionValidatorTest {

  private Booking booking;

  @BeforeEach
  void setUp() {
    this.booking = new Booking();
    MeetingRoom meetingRoom = new MeetingRoom();
    meetingRoom.setBookings(new ArrayList<>());
    this.booking.setMeetingRoom(meetingRoom);
  }

  @Test
  @DisplayName("Fails when booking is in the past")
  void testBookingIsInThePast() {

    Instant fromDate = Instant.now().minus(1, ChronoUnit.DAYS);
    Instant toDate = fromDate.plus(1, ChronoUnit.HOURS);

    booking.setFromDate(fromDate);
    booking.setToDate(toDate);

    BookingValidationException exception =
        assertThrows(
            BookingValidationException.class, () -> BookingDeletionValidator.validate(booking));
    assertTrue(exception.getMessage().contains("Cannot delete past booking."));
  }
}
