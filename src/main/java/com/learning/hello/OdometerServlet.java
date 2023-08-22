package com.learning.hello;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import com.learning.hello.controller.HiLoController;
import com.learning.hello.controller.Odometer;

@WebServlet("/Odometer")
public class OdometerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Odometer o;
	private JakartaServletWebApplication application;
	private TemplateEngine templateEngine;
    public OdometerServlet() {
        super();
        o = new Odometer(5);
    }
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
        application = JakartaServletWebApplication.buildApplication(getServletContext());
        final WebApplicationTemplateResolver templateResolver = 
            new WebApplicationTemplateResolver(application);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	var out = response.getWriter();
	    final IWebExchange webExchange = 
	        this.application.buildExchange(request, response);
	    final WebContext ctx = new WebContext(webExchange);
    	int reading = 0;
    	String readingType = request.getParameter("action");
    	if(readingType.equals("prev"))
    		o=o.prevReading();
    	if(readingType.equals("next"))
    		o=o.nextReading();
    	if(readingType.equals("reset")) {
    		int length;
			if(request.getParameter("digits")=="")
				length = 0;
    		else {
    			length = Integer.parseInt(request.getParameter("digits"));
    		}
			o = new Odometer(length);
    	}
    	reading = o.getReading();
	    ctx.setVariable("reading", reading);
	    templateEngine.process("Odometer", ctx, out);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	final IWebExchange webExchange = 
    	        this.application.buildExchange(request, response);
    	final WebContext ctx = new WebContext(webExchange);
	    ctx.setVariable("reading","12345");
	    templateEngine.process("Odometer", ctx, response.getWriter());
    }
}
