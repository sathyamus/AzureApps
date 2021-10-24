package com.snsystems.emailAlerts.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmailAlert {
	
    private int id;
	private String toAddress;
	private String mailSubject;
	private String mailBody;
	private boolean emailSent;

}
