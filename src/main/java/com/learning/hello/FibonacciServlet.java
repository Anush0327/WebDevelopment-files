package com.learning.hello;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Servlet implementation class FibonacciServlet
 */
public class FibonacciServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public int fibonacci(int n) {
		if(n == 0)
			return 1;
		else if(n == 1)
			return 1;
		else { 
			return fibonacci(n-1)+fibonacci(n-2);
			 
		}
	}
	public List<Integer> fibSeries(int n){
		List<Integer> series = new ArrayList<>();
		for(int i=0;i<n;i++) {
			series.add(fibonacci(i));
		}
		return series;
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			Map<String,String[]> parameterMap = request.getParameterMap();
			String number = "";
			if(parameterMap.get("n")==null) {
				number = new String("0");
			}
			else
				number = parameterMap.get("n")[0];
			PrintWriter out = response.getWriter();
			List<Integer> series= fibSeries(Integer.parseInt(number));
			out.print(String.format("<h1>fibonacci number for %d -> %s</h1>",Integer.parseInt(number),series));
			out.print(String.format("<h1>fibonacci number for %d -> %d</h1>",Integer.parseInt(number), fibonacci(Integer.parseInt(number))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String number = req.getParameter("limit");
		out.print(String.format("<p>%s</p>", fibSeries(Integer.parseInt(number))));
	}

}
