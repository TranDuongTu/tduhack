package com.tdu.hackerank.experiments.cdi2;

public class CheckoutEvent {

  private Order order;

  public CheckoutEvent(Order order) {
    this.order = order;
  }

  public Order getOrder() {
    return order;
  }

}