package com.antelif.acme.service.mapper;

import com.antelif.acme.model.entity.Booking;
import com.antelif.acme.model.entity.MeetingRoom;
import com.antelif.acme.model.request.BookingRequest;
import com.antelif.acme.model.response.BookingResponse;
import com.antelif.acme.service.MeetingRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingMapper implements Mapper<BookingRequest, Booking, BookingResponse> {

  private final MeetingRoomService meetingRoomService;

  @Override
  public Booking toEntity(BookingRequest request) {
    Booking entity = new Booking();
    entity.setToDate(request.getToDate());
    entity.setFromDate(request.getFromDate());
    entity.setEmployeeEmail(request.getEmployeeEmail());

    MeetingRoom meetingRoom = meetingRoomService.findById(request.getMeetingRoomId());
    entity.setMeetingRoom(meetingRoom);
    meetingRoom.getBookings().add(entity);

    return entity;
  }

  @Override
  public BookingResponse toResponse(Booking entity) {
    BookingResponse response = new BookingResponse();
    response.setId(entity.getId());
    response.setEmployeeEmail(entity.getEmployeeEmail());
    response.setDate(entity.getFromDate());
    response.setFromDate(entity.getFromDate());
    response.setToDate(entity.getToDate());
    response.setMeetingRoomId(entity.getMeetingRoom().getId());

    return response;
  }
}
