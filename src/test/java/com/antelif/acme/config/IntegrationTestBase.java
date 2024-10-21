package com.antelif.acme.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@Testcontainers
@AutoConfigureMockMvc
public class IntegrationTestBase {
  @Autowired protected ObjectMapper objectMapper;
  private static final ConfiguredPostgresContainer container;

  static {
    container = ConfiguredPostgresContainer.getInstance();
    container.start();
  }
}
