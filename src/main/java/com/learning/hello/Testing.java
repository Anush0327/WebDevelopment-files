package com.learning.hello;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.learning.hello.controller.Odometer;

public class Testing {
	public static void main(String[] args) {
		//System.out.println(Odometer.isAscending(1234));
		//System.out.println(Odometer.isAscending(1024));
		//System.out.println(Odometer.isAscending(1122));
		Odometer o1 = new Odometer(5);
		Odometer o2 = o1.nextReading();
		System.out.println(o1);
		System.out.println(o2);
		//System.out.println(0/0);
		try {
		o1.setReading(10234);
		} catch(Exception e) {
			System.out.println("I shouldn't have done that");
		}
	}
}
