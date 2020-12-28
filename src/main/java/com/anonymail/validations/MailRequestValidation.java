package com.anonymail.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.anonymail.dto.MailRequest;

@Component
public class MailRequestValidation {

	/**
	 * MailRequest Fields Validator.
	 * 
	 * @param mailRequest
	 * @param model
	 * @return
	 */
	public Model validateMailRequest(MailRequest mailRequest, Model model) {
		List<String> errors = new ArrayList();
		if (!isValid(mailRequest.getFrom())) {
			errors.add("there is an error in From field");
		}
		if (!isValid(mailRequest.getTo())) {
			errors.add("there is an error in To field");
		}
		if (mailRequest.getSubject().length() > 50) {
			errors.add("subject length cannot exceed 50 characters");
		}
		if (mailRequest.getText().length() > 400) {
			errors.add("text length cannot exceed 400 characters");

		}
		if (errors.size() > 0) {
			model.addAttribute("isValid", false);
			model.addAttribute("errorsList", errors);
		} else {
			model.addAttribute("isValid", true);
		}
		return model;

	}

	/**
	 * Email Validator
	 * 
	 * @param email
	 * @return
	 */
	public boolean isValid(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pat.matcher(email).matches();
	}

}
