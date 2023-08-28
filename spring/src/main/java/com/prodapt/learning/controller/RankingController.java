package com.prodapt.learning.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.prodapt.learning.model.Rankings.RankingModel;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/ranking")
public class RankingController {
	
	private RankingModel ranker = new RankingModel();
	
	@GetMapping
	public String Setvalue(Model model, HttpServletRequest req) {
		model.addAttribute("Student", ranker.getList());
		return "ranking";
	}

	@PostMapping
	public void processdata(HttpServletResponse resp, HttpServletRequest req) throws IOException {
		if ("enter".equals(req.getParameter("submit"))) {
			ranker.addStudent(req.getParameter("name"), req.getParameter("score"));
			ranker.setRanks();
		} else if ("Delete".equals(req.getParameter("button"))) {
			int studentIndex = Integer.parseInt(req.getParameter("studentIndex"));
			ranker.getList().remove(studentIndex);
			ranker.setRanks();
		} else if ("Edit".equals(req.getParameter("button"))) {
			int studentIndex = Integer.parseInt(req.getParameter("studentIndex"));
			String newName = req.getParameter("newName");
			String newScore = req.getParameter("newScore");
			ranker.updateStudent(studentIndex, newName, newScore);
			ranker.setRanks();
		}
		
		resp.sendRedirect("/ranking");
	}
}
