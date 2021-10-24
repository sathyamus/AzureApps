package com.snsystems.models;

public class EmailAlert {
	
    private int id;
	private String toAddress;
	private String mailSubject;
	private String mailBody;
	private boolean emailSent;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public String getMailSubject() {
		return mailSubject;
	}
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}
	public String getMailBody() {
		return mailBody;
	}
	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}
	public boolean isEmailSent() {
		return emailSent;
	}
	public void setEmailSent(boolean emailSent) {
		this.emailSent = emailSent;
	}
	
}
