package com.tduhack.javaee.cdi2;

public class LogConfiguration {

  private boolean infoMode;

  private boolean debugMode;

  public LogConfiguration(boolean infoMode, boolean debugMode) {
    this.infoMode = infoMode;
    this.debugMode = debugMode;
  }

  public boolean isInInfoMode() {
    return infoMode;
  }

  public boolean isInDebugMode() {
    return debugMode;
  }

}