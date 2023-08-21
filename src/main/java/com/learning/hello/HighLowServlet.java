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

@WebServlet("/HighLow")
public class HighLowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiLoController hlc;
	private JakartaServletWebApplication application;
	private TemplateEngine templateEngine;
    public HighLowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
        hlc = new HiLoController();
        hlc.reset();
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
    	 hlc.setGuess(Integer.parseInt(request.getParameter("guess")));
    	    var out = response.getWriter();
    	    final IWebExchange webExchange = 
    	        this.application.buildExchange(request, response);
    	    final WebContext ctx = new WebContext(webExchange);
    	    ctx.setVariable("feedback", hlc.feedback());
    	    templateEngine.process("highlowgame", ctx, out);
    	    if (hlc.judge() == 0)
    	      hlc.reset();
	}
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	final IWebExchange webExchange = 
    	        this.application.buildExchange(request, response);
    	final WebContext ctx = new WebContext(webExchange);
	    ctx.setVariable("feedback", "");
	    templateEngine.process("highlowgame", ctx, response.getWriter());
    }
}