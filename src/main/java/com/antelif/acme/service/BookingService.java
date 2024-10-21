package com.antelif.acme.service;

import com.antelif.acme.model.entity.Booking;
import com.antelif.acme.model.request.BookingRequest;
import com.antelif.acme.repository.BookingRepository;
import com.antelif.acme.service.mapper.BookingMapper;
import com.antelif.acme.service.validation.BookingValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Contains methods to get and persist booking room objects. */
@Service
@RequiredArgsConstructor
public class BookingService {
  private final BookingMapper mapper;
  private final BookingRepository repository;

  /**
   * Persists a new booking request.
   *
   * @param request contains necessary booking data
   * @return a BookingResponse of the newly persisted entity.
   */
  @Transactional
  public Booking addBooking(BookingRequest request) {
    Booking booking = mapper.toEntity(request);
    BookingValidator.validate(booking);
    return repository.save(booking);
  }
}
