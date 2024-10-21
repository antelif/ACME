package com.antelif.acme.repository;

import com.antelif.acme.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Booking repository. */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {}
