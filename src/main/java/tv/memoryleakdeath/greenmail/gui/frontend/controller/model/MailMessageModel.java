package tv.memoryleakdeath.greenmail.gui.frontend.controller.model;

import java.io.Serializable;
import java.util.Date;

public class MailMessageModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date receivedDate;
	private String subject;
	private String from;
	private String body;
	
	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
}
