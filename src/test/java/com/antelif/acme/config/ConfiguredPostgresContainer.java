package com.antelif.acme.config;

import org.testcontainers.containers.PostgreSQLContainer;

public class ConfiguredPostgresContainer extends PostgreSQLContainer<ConfiguredPostgresContainer> {
  private static ConfiguredPostgresContainer container;

  private ConfiguredPostgresContainer() {
    super("postgres:latest");
  }

  public static ConfiguredPostgresContainer getInstance() {
    if (container == null) {
      container = new ConfiguredPostgresContainer();
    }
    return container;
  }

  @Override
  public void start() {
    super.start();
    System.setProperty("spring.datasource.url", container.getJdbcUrl());
    System.setProperty("spring.datasource.username", container.getUsername());
    System.setProperty("spring.datasource.password", container.getPassword());
  }
}
