package com.antelif.acme.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/** The entity class that represents a MeetingRoom object as is in the database. */
@Entity
@Setter
@Getter
public class MeetingRoom extends BaseEntity<Long> {
  private String name;

  @OneToMany(mappedBy = "meetingRoom", orphanRemoval = true, cascade = CascadeType.ALL)
  private List<Booking> bookings = new ArrayList<>();
}
