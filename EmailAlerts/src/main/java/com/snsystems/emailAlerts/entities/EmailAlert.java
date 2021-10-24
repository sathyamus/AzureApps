package com.snsystems.emailAlerts.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

// https://learningprogramming.net/java/spring-boot/use-custom-query-with-spring-data-jpa-in-spring-boot/

@Setter
@Getter
@Entity
@Table(name = "EMAIL_ALERT")
public class EmailAlert implements Serializable {

    /**
	 */
	private static final long serialVersionUID = 1495243243978034175L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

    @Column(name="TO_ADDRESS")
	private String toAddress;

    @Column(name="MAIL_SUBJECT")
	private String mailSubject;
    
    @Column(name="MAIL_BODY")
	private String mailBody;

    @Column(name="IS_EMAIL_SENT")
	private boolean emailSent;

}
