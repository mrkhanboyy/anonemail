package com.anonymail.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@Entity
public class MailDetails {

	@Id
	private String id;
	@Column(nullable = false, updatable = false)
	private String Sendfrom;
	@Column(nullable = false, updatable = false)
	private String Sendto;
	@Column(nullable = false)
	private String subjectOfMail;
	@Lob
	@Column(nullable = false)
	private String textOfMail;

	private LocalDateTime DateTime;
	private String ipAddress;

	public MailDetails() {
		super();
	}

	public MailDetails(String id, String sendfrom, String sendto, String subjectOfMail, String textOfMail,
			LocalDateTime dateTime, String ipAddress) {
		super();
		this.id = id;
		Sendfrom = sendfrom;
		Sendto = sendto;
		this.subjectOfMail = subjectOfMail;
		this.textOfMail = textOfMail;
		DateTime = dateTime;
		this.ipAddress = ipAddress;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSendfrom() {
		return Sendfrom;
	}

	public void setSendfrom(String sendfrom) {
		Sendfrom = sendfrom;
	}

	public String getSendto() {
		return Sendto;
	}

	public void setSendto(String sendto) {
		Sendto = sendto;
	}

	public String getSubjectOfMail() {
		return subjectOfMail;
	}

	public void setSubjectOfMail(String subjectOfMail) {
		this.subjectOfMail = subjectOfMail;
	}

	public String getTextOfMail() {
		return textOfMail;
	}

	public void setTextOfMail(String textOfMail) {
		this.textOfMail = textOfMail;
	}

	public LocalDateTime getDateTime() {
		return DateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		DateTime = dateTime;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Override
	public String toString() {
		return "MailDetails [id=" + id + ", Sendfrom=" + Sendfrom + ", Sendto=" + Sendto + ", subjectOfMail="
				+ subjectOfMail + ", textOfMail=" + textOfMail + ", DateTime=" + DateTime + ", ipAddress=" + ipAddress
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DateTime == null) ? 0 : DateTime.hashCode());
		result = prime * result + ((Sendfrom == null) ? 0 : Sendfrom.hashCode());
		result = prime * result + ((Sendto == null) ? 0 : Sendto.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ipAddress == null) ? 0 : ipAddress.hashCode());
		result = prime * result + ((subjectOfMail == null) ? 0 : subjectOfMail.hashCode());
		result = prime * result + ((textOfMail == null) ? 0 : textOfMail.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MailDetails other = (MailDetails) obj;
		if (DateTime == null) {
			if (other.DateTime != null)
				return false;
		} else if (!DateTime.equals(other.DateTime))
			return false;
		if (Sendfrom == null) {
			if (other.Sendfrom != null)
				return false;
		} else if (!Sendfrom.equals(other.Sendfrom))
			return false;
		if (Sendto == null) {
			if (other.Sendto != null)
				return false;
		} else if (!Sendto.equals(other.Sendto))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ipAddress == null) {
			if (other.ipAddress != null)
				return false;
		} else if (!ipAddress.equals(other.ipAddress))
			return false;
		if (subjectOfMail == null) {
			if (other.subjectOfMail != null)
				return false;
		} else if (!subjectOfMail.equals(other.subjectOfMail))
			return false;
		if (textOfMail == null) {
			if (other.textOfMail != null)
				return false;
		} else if (!textOfMail.equals(other.textOfMail))
			return false;
		return true;
	}

}
