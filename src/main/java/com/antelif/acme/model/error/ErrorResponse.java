package com.antelif.acme.model.error;

import com.antelif.acme.model.error.exception.AcmeException;
import java.time.Instant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/** The API response when an exception occurs. */
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ErrorResponse {
  private String name;
  private int code;
  private String description;
  private int status;
  private Instant timestamp;

  /**
   * Constructor for validation exceptions.
   *
   * @param exception the exception that occurred,
   * @param status the http request status of the error that occurred,
   */
  public ErrorResponse(AcmeException exception, int status) {
    this.name = exception.getError().name();
    this.code = exception.getError().getCode();
    this.description = exception.getMessage();
    this.status = status;
    this.timestamp = Instant.now();
  }

  public ErrorResponse(AcmeError error, int status, String... args) {
    this.name = error.name();
    this.code = error.getCode();
    this.description =
        String.format(error.getDescription(), String.join(" ", args));
    this.status = status;
    this.timestamp = Instant.now();
  }
}
