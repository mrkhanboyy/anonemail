package com.anonymail.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anonymail.dto.MailRequest;
import com.anonymail.service.CustomMailService;
import com.anonymail.validations.MailRequestValidation;

@Controller
@RequestMapping("/mail")
public class MailController {

	private final CustomMailService mailService;
	private final MailRequestValidation mailRequestValidation;

	@Autowired
	public MailController(CustomMailService mailService, MailRequestValidation mailRequestValidation) {
		super();
		this.mailService = mailService;
		this.mailRequestValidation = mailRequestValidation;
	}

	/**
	 * Post Handler for sending mails.
	 * 
	 * @param mailRequest
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/send")
	public String sendMail(@ModelAttribute("MailRequest") MailRequest mailRequest, HttpServletRequest request,
			Model model, HttpSession session) {

		model = mailRequestValidation.validateMailRequest(mailRequest, model);
		model.addAttribute("content", "not-null");
		if ((boolean) model.getAttribute("isValid")) {
			String ipAddress = request.getHeader("X-FORWARDED-FOR");
			if (ipAddress == null) {
				ipAddress = request.getRemoteAddr();
			}
			mailService.sendMail(mailRequest, ipAddress);
			model.addAttribute("finally", "mail sent successfully");
			model.addAttribute("MailRequest", new MailRequest());
		} else {
			model.addAttribute("finally", "mail not sent due to following reasons : ");
			model.addAttribute("MailRequest", mailRequest);
		}

		return "home";
	}

}
