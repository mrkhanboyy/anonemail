package com.anonymail.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anonymail.dto.MailRequest;

@Controller
public class HomeController {

	/**
	 * GET Handler for home page.s
	 * 
	 * @param model
	 * @return homePgae
	 */
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("content", null);
		model.addAttribute("MailRequest", new MailRequest());
		return "home";
	}
}
