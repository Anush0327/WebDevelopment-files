package com.learning.hello.controller;

import java.util.HashMap;
import java.util.Map;

public class ControllerFactory {

  private static Map<String, IController> controllers;
  static {
    controllers = new HashMap<>();
    
  }
  public static IController get(String path) {
    return controllers.get(path);
  }
}