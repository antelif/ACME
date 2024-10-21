package com.antelif.acme.service.mapper;

/**
 * Maps acme objects to one another.
 *
 * @param <I> the request object,
 * @param <E> the entity object,
 * @param <O> the response object
 */
public interface Mapper<I, E, O> {

  /**
   * Maps a request object to an entity.
   *
   * @param i is the input that represents the request object.
   * @return an entity object.
   */
  E toEntity(I i);

  /**
   * Maps an entity object to a response.
   *
   * @param e is the entity object.
   * @return a reponse object.
   */
  O toResponse(E e);
}
