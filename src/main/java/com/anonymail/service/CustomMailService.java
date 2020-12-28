package com.anonymail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.anonymail.dto.MailRequest;
import com.anonymail.mapper.MailMapper;
import com.anonymail.outer.OuterLinks;
import com.anonymail.repository.MailDetailsRepository;

@Service
public class CustomMailService {

	private final MailDetailsRepository mailDetailsRepository;
	private final MailMapper mailMapper;
	private final OuterLinks outer;

	@Autowired
	public CustomMailService(MailDetailsRepository mailDetailsRepository, MailMapper mailMappers, OuterLinks outer) {
		super();
		this.mailDetailsRepository = mailDetailsRepository;
		this.mailMapper = new MailMapper();
		this.outer = outer;
	}

	/**
	 * Handler to save data to database and send mail.
	 * 
	 * @param mailRequest
	 * @param ipAddress
	 */
	@Async
	public void sendMail(MailRequest mailRequest, String ipAddress) {
		mailDetailsRepository.save(mailMapper.MailRequestToMailDetails(mailRequest, ipAddress));
		outer.send(mailRequest);
	}

}
