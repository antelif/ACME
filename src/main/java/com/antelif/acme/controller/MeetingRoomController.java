package com.antelif.acme.controller;

import com.antelif.acme.model.entity.MeetingRoom;
import com.antelif.acme.model.request.MeetingRoomRequest;
import com.antelif.acme.model.response.MeetingRoomResponse;
import com.antelif.acme.service.MeetingRoomService;
import com.antelif.acme.service.mapper.MeetingRoomMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/acme/meeting-room")
public class MeetingRoomController {

  private final MeetingRoomService meetingRoomService;
  private final MeetingRoomMapper mapper;

  /**
   * Persists a new meeting room request.
   *
   * @param request contains necessary meeting room data
   * @return a response with information about the new meeting room.
   */
  @PostMapping(value = "/")
  public ResponseEntity<MeetingRoomResponse> addMeetingRoom(
      @RequestBody @Valid MeetingRoomRequest request) {
    log.info("Request to persist new meeting room {}", request);

    MeetingRoom meetingRoom = meetingRoomService.addMeetingRoom(request);
    return ResponseEntity.ok(mapper.toResponse(meetingRoom));
  }
}
