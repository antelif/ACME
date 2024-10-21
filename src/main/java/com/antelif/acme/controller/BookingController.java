package com.antelif.acme.controller;

import com.antelif.acme.model.entity.Booking;
import com.antelif.acme.model.request.BookingRequest;
import com.antelif.acme.model.response.BookingResponse;
import com.antelif.acme.service.BookingService;
import com.antelif.acme.service.mapper.BookingMapper;
import jakarta.transaction.Transactional;
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
@RequestMapping(value = "/acme/booking")
public class BookingController {
  private final BookingService bookingService;
  private final BookingMapper mapper;

  /**
   * Persists a new booking request.
   *
   * @param request contains necessary booking data
   * @return a response with information about the new booking.
   */
  @PostMapping(value = "/")
  @Transactional
  public ResponseEntity<BookingResponse> addBooking(@RequestBody BookingRequest request) {
    log.info("Request to persist new booking {}", request);

    Booking booking = bookingService.addBooking(request);
    return ResponseEntity.ok(mapper.toResponse(booking));
  }
}
