package com.antelif.acme.controller;

import com.antelif.acme.model.entity.Booking;
import com.antelif.acme.model.request.BookingRequest;
import com.antelif.acme.model.response.BookingResponse;
import com.antelif.acme.service.BookingService;
import com.antelif.acme.service.mapper.BookingMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/acme/booking")
public class BookingController {
  private final BookingService bookingService;
  private final BookingMapper mapper;

  @GetMapping
  public ResponseEntity<Page<BookingResponse>> getBookings(
      @RequestParam("room") String meetingRoomName,
      @RequestParam("date") @DateTimeFormat(iso = ISO.DATE) Date bookingDate,
      Pageable page) {

    Instant date = bookingDate.toInstant();
    List<Booking> bookings = bookingService.getBookingsByRoomAndDate(meetingRoomName, date, page);
    return ResponseEntity.ok(new PageImpl<>(bookings.stream().map(mapper::toResponse).toList()));
  }

  /**
   * Persists a new booking request.
   *
   * @param request contains necessary booking data
   * @return a response with information about the new booking.
   */
  @PostMapping(value = "/")
  @Transactional
  public ResponseEntity<BookingResponse> addBooking(@RequestBody @Valid BookingRequest request) {
    log.info("Request to persist new booking {}", request);

    Booking booking = bookingService.addBooking(request);
    return ResponseEntity.ok(mapper.toResponse(booking));
  }

  /**
   * Deletes an existing booking by booking id.
   *
   * @param id the booking id.
   * @return a message regarding the successful completion.
   */
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<String> cancelBooking(@PathVariable Long id) {
    log.info("Request to cancel booking by id {}", id);
    bookingService.cancelBooking(id);
    return ResponseEntity.ok(String.format("Booking %s was deleted successfully.", id));
  }
}
