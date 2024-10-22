package com.antelif.acme.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class MeetingRoomResponse {
  private Long id;
  private String name;
}
