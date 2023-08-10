package com.learning.hello;
import java.io.IOException;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
public class HeartbeatServlet extends GenericServlet{

	@Override 
	public void init() {
		System.out.println("going to die");
	}
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("work till u die");
	}
	@Override
	public void destroy() {
		System.out.println("died");
	}

}
