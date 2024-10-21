package com.antelif.acme;

import org.springframework.boot.SpringApplication;

public class TestAcmeApplication {

  public static void main(String[] args) {
    SpringApplication.from(AcmeApplication::main).with(TestcontainersConfiguration.class).run(args);
  }

}
