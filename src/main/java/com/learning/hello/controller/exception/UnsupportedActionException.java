package com.learning.hello.controller.exception;


public class UnsupportedActionException extends Exception{
  private static final long serialVersionUID = 8022638161302046110L;

  public UnsupportedActionException(String message) {
    super(message);
  }
}