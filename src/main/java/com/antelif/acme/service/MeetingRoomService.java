package com.antelif.acme.service;

import com.antelif.acme.model.entity.MeetingRoom;
import com.antelif.acme.model.error.exception.CreationException;
import com.antelif.acme.model.error.exception.EntityDoesNotExistException;
import com.antelif.acme.model.error.exception.EntityExistsException;
import com.antelif.acme.model.request.MeetingRoomRequest;
import com.antelif.acme.repository.MeetingRoomRepository;
import com.antelif.acme.service.mapper.MeetingRoomMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Contains methods to get and persist meeting room objects. */
@Service
@RequiredArgsConstructor
public class MeetingRoomService {
  private final MeetingRoomMapper mapper;
  private final MeetingRoomRepository repository;

  /**
   * Persists a new meeting room request.
   *
   * @param request contains necessary meeting room data
   * @return a MeetingRoom of the newly persisted entity.
   */
  @Transactional
  public MeetingRoom addMeetingRoom(MeetingRoomRequest request) {
    Optional<MeetingRoom> persistedMeetingRoom = repository.findByName(request.getName());

    if (persistedMeetingRoom.isPresent()) {
      throw new EntityExistsException(request.toString());
    }

    return Optional.of(request)
        .map(mapper::toEntity)
        .map(repository::save)
        .orElseThrow(() -> new CreationException(request.toString()));
  }

  @Transactional
  public MeetingRoom findById(Long id) {

    return repository
        .findById(id)
        .orElseThrow(() -> new EntityDoesNotExistException("Meeting room with id " + id));
  }
}
