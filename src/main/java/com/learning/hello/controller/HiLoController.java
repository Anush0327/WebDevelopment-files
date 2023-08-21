package com.learning.hello.controller;

import java.util.Random;

public class HiLoController {
  private int correct;
  private int guess;
  
  public void setGuess(int guess) {
    this.guess = guess;
  }
  
  public void reset() {
    this.correct = new Random().nextInt(0, 100);
  }
  
  public int judge() {
    if(correct == guess) 
    	return 0;
    else if(correct > guess)
    	return -1;
    else
    	return 1;
  }
  
  public String feedback() {
    if(judge() == 0)
    	return "Correct Guess --> " + guess;
    else if(judge() == 1)
    	return "Guess Lower";
    else
    	return "Guess Higher";
  }
}