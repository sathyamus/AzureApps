package com.snsystems.emailAlerts.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.jdbc.Sql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.snsystems.emailAlerts.entities.EmailAlert;

@DataJpaTest
public class EmailAlertRepositoryTest {
	
	@Autowired
	private EmailAlertRepository emailAlertRepository;
	
	@BeforeTestMethod
	public void contextLoad() {
		assertThat(emailAlertRepository).isNotNull();
	}
	
	@Test
	public void create_emailAlerts() throws JsonProcessingException {
		
		EmailAlert emailAlert = new EmailAlert();
		emailAlert.setEmailSent(true);
		emailAlert.setMailBody("John");
		emailAlert.setMailSubject("Smith");
		emailAlert.setToAddress("John.Smith@gmail.com");
		emailAlertRepository.save(emailAlert);
		
		assertThat(emailAlertRepository.count()).isEqualTo(1);
	}
	
	@Test
	@Sql("classpath:create_email_alert.sql")
	public void verify_added_emailAlerts() {
		
		List<EmailAlert> emailAlerts = emailAlertRepository.findByToAddress("Jeff.Mayer@gmail.com");
		EmailAlert fetchedCustomerEmailAlert = emailAlerts.get(0);
		assertThat(fetchedCustomerEmailAlert.getId()).isEqualTo(100);
		assertThat(fetchedCustomerEmailAlert.getToAddress()).isEqualTo("Jeff.Mayer@gmail.com");
		assertThat(fetchedCustomerEmailAlert.getMailSubject()).isEqualTo("Jeff");
		assertThat(fetchedCustomerEmailAlert.getMailBody()).isEqualTo("Mayer");		
		assertThat(fetchedCustomerEmailAlert.isEmailSent()).isEqualTo(true);		
	}

	@Test
	@Sql("classpath:create_email_alert.sql")
	public void find_emailAlert_by_id() {
		Optional<EmailAlert> emailAlert = emailAlertRepository.findById(100);
		assertThat(emailAlert.isPresent()).isEqualTo(true);
		EmailAlert fetchedCustomerEmailAlert = null;
		if (emailAlert.isPresent()) {
			fetchedCustomerEmailAlert = (EmailAlert) emailAlert.get();
			assertThat(fetchedCustomerEmailAlert.getId()).isEqualTo(100);
			assertThat(fetchedCustomerEmailAlert.getToAddress()).isEqualTo("Jeff.Mayer@gmail.com");
			assertThat(fetchedCustomerEmailAlert.getMailSubject()).isEqualTo("Jeff");
			assertThat(fetchedCustomerEmailAlert.getMailBody()).isEqualTo("Mayer");		
			assertThat(fetchedCustomerEmailAlert.isEmailSent()).isEqualTo(true);	
		}
	}
	
}
