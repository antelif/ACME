package com.antelif.acme.controller;

import static com.antelif.acme.model.error.AcmeError.ENTITY_DOES_NOT_EXIST_ERROR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.antelif.acme.config.IntegrationTestBase;
import com.antelif.acme.model.error.ErrorResponse;
import com.antelif.acme.model.request.BookingRequest;
import com.antelif.acme.model.request.MeetingRoomRequest;
import com.antelif.acme.model.response.BookingResponse;
import com.antelif.acme.model.response.MeetingRoomResponse;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@DisplayName("Booking controller")
class BookingControllerIT extends IntegrationTestBase {

  @Autowired private MockMvc mockMvc;
  @Autowired private WebApplicationContext webApplicationContext;

  private BookingRequest bookingRequest;
  private MeetingRoomRequest meetingRoomRequest;

  @BeforeEach
  void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    this.bookingRequest = new BookingRequest();
    this.meetingRoomRequest = new MeetingRoomRequest();
  }

  @Test
  @DisplayName("Booking: Successful creation")
  @SneakyThrows
  void testBookingIsCreatedSuccessfully() {
    // Create meeting room
    this.meetingRoomRequest.setName("testBookingIsCreatedSuccessfully");
    String meetingRoomResponseAsString = postMeetingRoom(meetingRoomRequest);
    Long meetingRoomId =
        objectMapper.readValue(meetingRoomResponseAsString, MeetingRoomResponse.class).getId();

    // Create booking for the above meeting room id
    Instant fromDate = Instant.now().plus(1, ChronoUnit.DAYS);
    Instant toDate = fromDate.plus(1, ChronoUnit.HOURS);
    this.bookingRequest.setFromDate(fromDate);
    this.bookingRequest.setToDate(toDate);
    this.bookingRequest.setEmployeeEmail("something@mail.com");
    this.bookingRequest.setMeetingRoomId(meetingRoomId);

    String bookingResponseAsString = postBooking(bookingRequest);
    BookingResponse response =
        objectMapper.readValue(bookingResponseAsString, BookingResponse.class);

    assertNotNull(response.getId());
    assertEquals(meetingRoomId, response.getMeetingRoomId());
    assertEquals("something@mail.com", response.getEmployeeEmail());
    assertEquals(fromDate, response.getFromDate());
    assertEquals(toDate, response.getToDate());
  }

  @Test
  @DisplayName("Booking: Successful deletion")
  @SneakyThrows
  void testBookingIsDeletedSuccessfully() {
    // Create meeting room
    this.meetingRoomRequest.setName("testBookingIsDeletedSuccessfully");
    String meetingRoomResponseAsString = postMeetingRoom(meetingRoomRequest);
    Long meetingRoomId =
        objectMapper.readValue(meetingRoomResponseAsString, MeetingRoomResponse.class).getId();

    // Create booking for the above meeting room id
    Instant fromDate = Instant.now().plus(1, ChronoUnit.DAYS);
    Instant toDate = fromDate.plus(1, ChronoUnit.HOURS);
    this.bookingRequest.setFromDate(fromDate);
    this.bookingRequest.setToDate(toDate);
    this.bookingRequest.setEmployeeEmail("something@mail.com");
    this.bookingRequest.setMeetingRoomId(meetingRoomId);

    String bookingResponseAsString = postBooking(bookingRequest);
    Long bookingId = objectMapper.readValue(bookingResponseAsString, BookingResponse.class).getId();

    assertNotNull(bookingId);

    // Delete it
    MockHttpServletResponse response = deleteBooking(bookingId);
    assertEquals(200, response.getStatus());
    assertTrue(response.getContentAsString().contains("was deleted successfully"));
  }

  @Test
  @DisplayName("Booking: Deletion of booking that does not exist should fail")
  @SneakyThrows
  void testBookingIsNotDeletedWhenIdDoesNotExist() {

    // Try to delete a kind of impossible id
    String errorResponseAsString = deleteBooking(99999L).getContentAsString();
    ErrorResponse errorResponse =
        objectMapper.readValue(errorResponseAsString, ErrorResponse.class);

    assertEquals(ENTITY_DOES_NOT_EXIST_ERROR.name(), errorResponse.getName());
  }

  @SneakyThrows
  private String postBooking(BookingRequest request) {
    return this.mockMvc
        .perform(
            post("/acme/booking/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andDo(print())
        .andReturn()
        .getResponse()
        .getContentAsString();
  }

  @SneakyThrows
  private MockHttpServletResponse deleteBooking(Long bookingId) {
    return this.mockMvc
        .perform(delete("/acme/booking/" + bookingId))
        .andDo(print())
        .andReturn()
        .getResponse();
  }

  @SneakyThrows
  private String postMeetingRoom(MeetingRoomRequest request) {
    return this.mockMvc
        .perform(
            post("/acme/meeting-room/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andDo(print())
        .andReturn()
        .getResponse()
        .getContentAsString();
  }
}
