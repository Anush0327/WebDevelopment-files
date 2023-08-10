package com.learning.hello;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Servlet implementation class NoticesServlet
 */
public class NoticesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String[]> parameterMap = request.getParameterMap();
		String name = parameterMap.get("name")[0];
		String title = parameterMap.get("title")[0];
		String content = parameterMap.get("content")[0];
		String phone = parameterMap.get("phone")[0];
		PrintWriter out = response.getWriter();
		out.print(String.format("<p>%s</p>"
				+ "<p>%s</p>"
				+ "<p>%s</p>"
				+ "<p>%s</p>",title,content,name,phone));
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
			PrintWriter out = resp.getWriter();
			out.print(String.format("<p>%s</p>"
				+ "<p>%s</p>"
				+ "<p>%s</p>"
				+ "<p>%s</p>", req.getParameter("title"),req.getParameter("content"),req.getParameter("name"),req.getParameter("phone")));
		}
}
