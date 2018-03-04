package com.tduhack.javaee.cdi2;

import javax.enterprise.inject.Produces;

public class SpecialLoggerFactory {

  @Produces
  public SpecialLogger createLogger() {
    LogConfiguration logInDebugMode = new LogConfiguration(true, false);

    return new SpecialLogger(logInDebugMode);
  }
}
