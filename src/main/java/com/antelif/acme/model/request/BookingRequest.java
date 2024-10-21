package com.antelif.acme.model.request;

import java.time.Instant;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents the request body when creating a new booking.
 */
@ToString
@Getter
@Setter
public class BookingRequest {
  private Instant fromDate;
  private Instant toDate;
  private String employeeEmail;
  private Long meetingRoomId;
}
