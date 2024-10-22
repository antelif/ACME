package com.antelif.acme.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;

/** Represents the API response after creating a new booking. */
@Getter
@Setter
public class BookingResponse {
  private Long id;

  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
  private Instant date;

  @JsonFormat(pattern = "HH:mm", timezone = "UTC")
  private Instant fromDate;

  @JsonFormat(pattern = "HH:mm", timezone = "UTC")
  private Instant toDate;

  private String employeeEmail;
  private Long meetingRoomId;
}
