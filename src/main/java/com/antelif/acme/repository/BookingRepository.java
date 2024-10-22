package com.antelif.acme.repository;

import com.antelif.acme.model.entity.Booking;
import java.time.Instant;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Booking repository. */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
  List<Booking> findByMeetingRoomNameAndFromDateAfter(
      String meetingRoomName, Instant fromDate, Pageable pageable);
}
