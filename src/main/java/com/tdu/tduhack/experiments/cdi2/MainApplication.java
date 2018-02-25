package com.tdu.tduhack.experiments.cdi2;

import org.jboss.weld.environment.se.Weld;

import javax.enterprise.inject.spi.CDI;

public class MainApplication {

  public static void main(String[] args) throws Exception {
    final CDI<Object> container = new Weld().initialize();
    Checkout checkout = container.select(Checkout.class).get();
    final Buyer buyer = new Buyer("welcome@hackingcode.com");
    final Order order = new Order(buyer, 10L, 80.0);

    checkout.finishCheckout(order);
  }
}
