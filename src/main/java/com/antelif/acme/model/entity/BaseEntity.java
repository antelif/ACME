package com.antelif.acme.model.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/** The base class that contains all meta fields of an entity. */
@MappedSuperclass
@Getter
@Setter
public class BaseEntity<T> {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private T id;

  @CreationTimestamp private Instant creationDate;
  @UpdateTimestamp private Instant updatedDate;
}
