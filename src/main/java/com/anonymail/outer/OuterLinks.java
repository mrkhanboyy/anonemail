package com.anonymail.outer;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.anonymail.dto.MailRequest;

@Component
public class OuterLinks {

	/**
	 * list of api keys
	 */
	private final String apiKeys[] = new String[] { "be334d60c0mshcabe1573c4c412bp13ae4ejsne949ea8e17b2",
			"6109907f38mshc65bffbf92f6defp127d07jsnd2a5d35663c7", "723b14b307msh32726cab92efea5p1a6adbjsn24b49c0c5cf6",
			"87f4d3eb58mshd91fb13da4f32dfp12ec03jsn4e8a6d4e0366" };
	
	/**
	 * API URL to send mails
	 */
	private final String uri = "https://rapidprod-sendgrid-v1.p.rapidapi.com/mail/send";
	private final String rapiApiHost = "rapidprod-sendgrid-v1.p.rapidapi.com";

	public AtomicInteger total = new AtomicInteger(0);

	private final RestTemplate restTemplate;

	@Autowired
	public OuterLinks(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	/**
	 * Method to send mails.
	 * 
	 * @param mailRequest
	 */
	public void send(MailRequest mailRequest) {
		if (getTotal() >= 190) {
			return;
		} else {
			increaseTotal();
		}

		String requestJson = "{\n    \"personalizations\": [\n        {\n            \"to\": [\n                {\n                    \"email\": \""
				+ mailRequest.getTo() + "\"\n                }\n            ],\n            \"subject\": \""
				+ mailRequest.getSubject() + "\"\n        }\n    ],\n    \"from\": {\n        \"email\": \""
				+ mailRequest.getFrom()
				+ "\"\n    },\n    \"content\": [\n        {\n            \"type\": \"text/plain\",\n            \"value\": \""
				+ mailRequest.getText() + "\"\n        }\n    ]\n}";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("x-rapidapi-key", apiKeys[getTotal() % 4]);
		headers.set("x-rapidapi-host", rapiApiHost);
		headers.setConnection("keep-alive");
		headers.setContentLength(requestJson.length());
		headers.set("Host", "rapidprod-sendgrid-v1.p.rapidapi.com");
		headers.setOrigin("");
		headers.set("Referer", "https://rapidapi.com/");
		headers.set("sec-ch-ua", "\"Google Chrome\";v=\"87\", \"\\\"Not;A\\\\Brand\";v=\"99\", \"Chromium\";v=\"87\"");
		headers.set("sec-ch-ua-mobile", "?1");
		headers.set("Sec-Fetch-Dest", "empty");
		headers.set("Sec-Fetch-Mode", "cors");
		headers.set("Sec-Fetch-Site", "same-site");
		headers.set("useQueryString", "true");
		headers.set("User-Agent",
				"Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Mobile Safari/537.36");
		headers.set("x-rapidapi-ua", "RapidAPI-Playground");

		HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
		restTemplate.postForObject(uri, entity, String.class);
	}

	/**
	 * 
	 * @return atomic variable {total} value
	 */
	private int getTotal() {
		return total.get();
	}

	/**
	 * increments the value of atomic variable {total} by 1;
	 */
	private void increaseTotal() {
		total.incrementAndGet();
	}

	/**
	 *  setting atomic variable {total} value to 1 
	 * @param value value to set
	 */
	private void setToInitial(int value) {
		total.set(value);
	}

	/**
	 * method setting atomic variable {total} value to 1 every 23:59 P.M 
	 * 
	 */
	@Scheduled(cron = "0 59 23 * * *", zone = "Indian/Maldives")
	private void cronJob() {
		setToInitial(1);
	}
}
