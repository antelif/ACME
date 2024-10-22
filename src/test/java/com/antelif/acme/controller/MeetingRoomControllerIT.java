package com.antelif.acme.controller;

import static com.antelif.acme.model.error.AcmeError.ENTITY_EXISTS_ERROR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.antelif.acme.config.IntegrationTestBase;
import com.antelif.acme.model.error.ErrorResponse;
import com.antelif.acme.model.request.MeetingRoomRequest;
import com.antelif.acme.model.response.MeetingRoomResponse;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@DisplayName("MeetingRoom controller")
class MeetingRoomControllerIT extends IntegrationTestBase {

  @Autowired private MockMvc mockMvc;
  @Autowired private WebApplicationContext webApplicationContext;

  private MeetingRoomRequest request;

  @BeforeEach
  void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    this.request = new MeetingRoomRequest();
  }

  @Test
  @DisplayName("MeetingRoom: Successful creation")
  @SneakyThrows
  void testMeetingRoomIsCreatedSuccessfully() {

    this.request.setName("testMeetingRoomIsCreatedSuccessfully");
    String stringResponse = postMeetingRoom(request);
    MeetingRoomResponse response =
        objectMapper.readValue(stringResponse, MeetingRoomResponse.class);
    assertEquals("testMeetingRoomIsCreatedSuccessfully", response.getName());
    assertNotNull(response.getId());
  }

  @Test
  @DisplayName("MeetingRoom: Duplicate meeting room is not created")
  @SneakyThrows
  void testDuplicateMeetingRoomIsNotCreated() {

    // Create first meeting room
    this.request.setName("testDuplicateMeetingRoomIsNotCreated");
    String firstMeetingRoomAsString = postMeetingRoom(request);
    MeetingRoomResponse firstMeetingRoom =
        objectMapper.readValue(firstMeetingRoomAsString, MeetingRoomResponse.class);
    assertEquals("testDuplicateMeetingRoomIsNotCreated", firstMeetingRoom.getName());

    // Create second meeting room with same name
    String stringResponse = postMeetingRoom(request);
    ErrorResponse response = objectMapper.readValue(stringResponse, ErrorResponse.class);
    assertEquals(ENTITY_EXISTS_ERROR.name(), response.getName());
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
