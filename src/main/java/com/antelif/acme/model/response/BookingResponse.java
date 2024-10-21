package com.antelif.acme.model.response;

import java.time.Instant;
import lombok.Getter;
import lombok.Setter;

/** Represents the API response after creating a new booking. */
@Getter
@Setter
public class BookingResponse {
  private Long id;
  private Instant fromDate;
  private Instant toDate;
  private String employeeEmail;
  private Long meetingRoomId;
}
