package com.antelif.acme.service;

import com.antelif.acme.model.entity.Booking;
import com.antelif.acme.model.error.exception.EntityDoesNotExistException;
import com.antelif.acme.model.request.BookingRequest;
import com.antelif.acme.repository.BookingRepository;
import com.antelif.acme.service.mapper.BookingMapper;
import com.antelif.acme.service.validation.BookingCreationValidator;
import com.antelif.acme.service.validation.BookingDeletionValidator;
import jakarta.transaction.Transactional;
import java.util.Optional;
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
    BookingCreationValidator.validate(booking);
    return repository.save(booking);
  }

  /**
   * Deletes an existing booking by booking id.
   *
   * @param id the booking id.
   */
  @Transactional
  public void cancelBooking(Long id) {
    Optional<Booking> persistedBooking = repository.findById(id);
    if (persistedBooking.isEmpty()) {
      throw new EntityDoesNotExistException("booking with id " + id);
    }

    BookingDeletionValidator.validate(persistedBooking.get());
    repository.deleteById(id);
  }
}
