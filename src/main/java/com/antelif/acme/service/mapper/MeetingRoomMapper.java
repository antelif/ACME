package com.antelif.acme.service.mapper;

import com.antelif.acme.model.entity.MeetingRoom;
import com.antelif.acme.model.request.MeetingRoomRequest;
import com.antelif.acme.model.response.MeetingRoomResponse;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class MeetingRoomMapper
    implements Mapper<MeetingRoomRequest, MeetingRoom, MeetingRoomResponse> {

  @Override
  public MeetingRoom toEntity(MeetingRoomRequest request) {
    MeetingRoom entity = new MeetingRoom();
    entity.setName(request.getName());
    entity.setBookings(new ArrayList<>());
    return entity;
  }

  @Override
  public MeetingRoomResponse toResponse(MeetingRoom entity) {
    MeetingRoomResponse response = new MeetingRoomResponse();
    response.setId(entity.getId());
    response.setName(entity.getName());

    return response;
  }
}
