package com.learning.hello;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PasswordConfirmation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
		List<String> passwords= Files.lines(Path.of("/home/abbagownianush/eclipse-workspace/notices-two/src/passwords.txt")).toList();
		PrintWriter out = response.getWriter();
		if(passwords.contains(request.getParameter("pwd")))
			out.print(String.format("<p>failure,This password already exist - %s</p>", request.getParameter("pwd")));
		else {
			FileWriter write = new FileWriter(new File("/home/abbagownianush/eclipse-workspace/notices-two/src/passwords.txt"));
			write.write(request.getParameter("pwd"));
			out.print("Successful Attempt");
			write.close();
		}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
