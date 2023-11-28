package com.medical.dentist.instance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(
    basePackages = {
        "com.medical.dentist.module"
    })
public class DentistApplication {

  public static void main(String[] args) {
    SpringApplication.run(DentistApplication.class, args);
  }

}
