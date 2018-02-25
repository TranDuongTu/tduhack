package com.tdu.tduhack.javaee.cdi2;

import javax.enterprise.event.Observes;

public class MetricCreator {
  
  public void createMetricFor(@Observes CheckoutEvent event) {
    System.out.println("Creating new Metric for OrderId: " + event.getOrder());
  }
}
