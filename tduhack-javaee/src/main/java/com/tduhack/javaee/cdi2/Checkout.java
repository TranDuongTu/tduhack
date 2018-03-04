package com.tduhack.javaee.cdi2;

import javax.enterprise.event.Event;
import javax.inject.Inject;

public class Checkout {
  @Inject
  private SpecialLogger logger;

  @Inject
  private Event<CheckoutEvent> event;

  public void finishCheckout(final Order order) {
    logger.log("Finishing Checkout with Id: " + order.getId());
    event.fire(new CheckoutEvent(order));
  }
}
