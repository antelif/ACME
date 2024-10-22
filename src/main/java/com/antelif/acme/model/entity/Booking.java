package com.antelif.acme.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.Duration;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;

/** The entity class that represents a Booking object as is in the database. */
@Entity
@Setter
@Getter
public class Booking extends BaseEntity<Long> {
  private String employeeEmail;
  private Instant fromDate;
  private Instant toDate;

  @ManyToOne
  @JoinColumn(name = "meeting_room_id")
  MeetingRoom meetingRoom;

  public Duration meetingDuration() {
    return Duration.between(fromDate, toDate);
  }

  /**
   * Checks if a different booking overlaps this one.
   *
   * @param otherBooking a different booking than this.
   * @return true if the bookings overlap.
   */
  public boolean overlaps(Booking otherBooking) {
    if (otherBooking.equals(this)) {
      return false;
    }
    return fromDate.isBefore(otherBooking.toDate) && otherBooking.fromDate.isBefore(toDate);
  }
}
