package com.antelif.acme.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** Represents the request body when creating a new booking. */
@ToString
@Getter
@Setter
public class BookingRequest {
  @NotNull(message = "Booking from date cannot be null.")
  private Instant fromDate;

  @NotNull(message = "Booking to date cannot be null.")
  private Instant toDate;

  @NotBlank(message = "Booking employee email cannot be null.")
  private String employeeEmail;

  @NotNull(message = "Booking meeting room id cannot be null.")
  private Long meetingRoomId;
}
