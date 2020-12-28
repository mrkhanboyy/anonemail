package com.anonymail.mapper;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.anonymail.dto.MailRequest;
import com.anonymail.model.MailDetails;

@Component
public class MailMapper {

	/**
	 * MailRequest data to MailDetails class Mapper 
	 * 
	 * @param mailRequest
	 * @param ipAdderss
	 * @return
	 */
	public MailDetails MailRequestToMailDetails(MailRequest mailRequest, String ipAdderss) {

		MailDetails md = new MailDetails();
		md.setId(UUID.randomUUID().toString());
		md.setSendfrom(mailRequest.getFrom());
		md.setSendto(mailRequest.getTo());
		md.setSubjectOfMail(mailRequest.getSubject());
		md.setTextOfMail(mailRequest.getText());
		md.setDateTime(LocalDateTime.now());
		md.setIpAddress(ipAdderss);
		return md;
	}

}
