package com.tdu.hackerank.experiments.cdi2;

import javax.enterprise.event.Observes;

public class EmailSender {

  public void sendEmailFor(@Observes CheckoutEvent event) {
    System.out.println("Sending email to: " + event.getOrder().getBuyer().getEmail());
  }
}
