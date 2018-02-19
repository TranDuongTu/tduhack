package com.tdu.hackerank.experiments.cdi2;

public class SpecialLogger {

  private final LogConfiguration configuration;

  public SpecialLogger(final LogConfiguration configuration) {
    this.configuration = configuration;
  }

  public void log(String message) {
    if (configuration.isInDebugMode()) {
      System.out.println("DEBUG LOG: " + message);
    } else if (configuration.isInInfoMode()) {
      System.out.println("DEBUG LOG: " + message);
    } else {
      System.out.println("DEFAULT LOG: " + message);
    }
  }
}
