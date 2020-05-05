package com.github.schuettec.eventmulticaster;

public interface SubscriberType {

  public void notified() throws Exception;

  public void noException();

}
