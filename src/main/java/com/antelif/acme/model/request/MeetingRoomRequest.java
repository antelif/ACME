package com.antelif.acme.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class MeetingRoomRequest {
  @NotBlank(message = "Booking meeting room id cannot be null.")
  private String name;
}
