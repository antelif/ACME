package com.antelif.acme.repository;

import com.antelif.acme.model.entity.MeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Meeting room repository. */
@Repository
public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Long> {}
