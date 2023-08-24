package com.learing.mangatha;

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

import com.learing.mangatha.controller.MangathaController;

@WebServlet("/mangatha")
public class MangathaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MangathaController mc;
	private JakartaServletWebApplication application;
	private TemplateEngine templateEngine;
	@Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	mc = new MangathaController();
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var out = resp.getWriter();
		String incard = " ",outcard = " ";
	    final IWebExchange webExchange = 
	        this.application.buildExchange(req, resp);
	    final WebContext ctx = new WebContext(webExchange);
	    ctx.setVariable("shouldDisableDeck", mc.isGameOver());
	    if("Submit".equals(req.getParameter("action"))) {
	    	mc.addPlayers(req.getParameter("bet1"),req.getParameter("card1"),req.getParameter("inOut1"),"Player1");
	    	mc.addPlayers(req.getParameter("bet2"),req.getParameter("card2"),req.getParameter("inOut2"),"Player2");
	    	outcard = Character.toString(0x1F0A0);
	    	
	    	incard = Character.toString(0x1F0A0);
	    }
	    if(("🂠").equals(req.getParameter("Deck"))) {
	    	
	    	if(mc.isFlag()) {
	    		incard = mc.PlayCard();
	    		outcard = Character.toString(0x1F0A0);
	    	}
	    	else {
	    		incard = Character.toString(0x1F0A0);
	    		outcard = mc.PlayCard();
	    	}
	    }
	    ctx.setVariable("inCard", incard);
	    ctx.setVariable("outCard", outcard);
	    String val = mc.validate();
	    if( (val != "Place next card" )|| mc.isEmpty()) {
	    	ctx.setVariable("msg", val);
	    	ctx.setVariable("shouldDisableDeck", mc.isGameOver());
	    }
	    templateEngine.process("mangatha",ctx,out);   
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		var out = resp.getWriter();
	    final IWebExchange webExchange = 
	        this.application.buildExchange(req, resp);
	    final WebContext ctx = new WebContext(webExchange);	  
	    ctx.setVariable("shouldDisableDeck", mc.isGameOver());
	    ctx.setVariable("inCard",Character.toString(0x1F0A0));
	    ctx.setVariable("outCard",Character.toString(0x1F0A0));
	    templateEngine.process("mangatha", ctx,out);
	}
}