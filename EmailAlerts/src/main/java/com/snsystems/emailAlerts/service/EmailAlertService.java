package com.snsystems.emailAlerts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snsystems.emailAlerts.entities.EmailAlert;
import com.snsystems.emailAlerts.repositories.EmailAlertRepository;

@Service
@Slf4j
public class EmailAlertService {

	@Autowired
	private EmailAlertRepository emailAlertRepository;

	public List<com.snsystems.emailAlerts.models.EmailAlert> findAll() {
		List<com.snsystems.emailAlerts.models.EmailAlert> emailAlerts = new ArrayList<com.snsystems.emailAlerts.models.EmailAlert>();
		emailAlertRepository.findAll().stream().forEach(input -> {
			emailAlerts.add(convertEntityToModel(input));
		});
		return emailAlerts;
	}

	public com.snsystems.emailAlerts.models.EmailAlert findById(Integer emailAlertId) {
		Optional<EmailAlert> emailAlert = emailAlertRepository.findById(emailAlertId);
		com.snsystems.emailAlerts.entities.EmailAlert emailAlertEntity = null;
		com.snsystems.emailAlerts.models.EmailAlert emailAlertModel = null;
		if (emailAlert.isPresent()) {
			emailAlertEntity = emailAlert.get();
			emailAlertModel = convertEntityToModel(emailAlertEntity);
		}
		return Optional.ofNullable(emailAlertModel).get();
	}
	
	public com.snsystems.emailAlerts.models.EmailAlert findByToAddress(String name) {
		return convertEntityToModel(emailAlertRepository.findByToAddress(name).get(0));
	}
	
	public com.snsystems.emailAlerts.models.EmailAlert addEmailAlert(com.snsystems.emailAlerts.models.EmailAlert emailAlertModel) {
		com.snsystems.emailAlerts.entities.EmailAlert emailAlertEntity = convertModelToEntity(emailAlertModel);
		emailAlertEntity = emailAlertRepository.save(emailAlertEntity);
		emailAlertModel = convertEntityToModel(emailAlertEntity);
		return emailAlertModel;
	}
	
	public boolean deleteById(Integer emailAlertId) {
		boolean isDeleted = false;
		emailAlertRepository.deleteById(emailAlertId);
		Optional<EmailAlert> emailAlert = emailAlertRepository.findById(emailAlertId);
		if (!emailAlert.isPresent()) {
			log.info("Successfully Deleted EmailAlert with emailAlertId: {}", emailAlertId);
			isDeleted = true;
		}
		return isDeleted;
	}

	private com.snsystems.emailAlerts.models.EmailAlert convertEntityToModel(EmailAlert emailAlertEntity) {
		com.snsystems.emailAlerts.models.EmailAlert emailAlertModel = new com.snsystems.emailAlerts.models.EmailAlert();
		emailAlertModel.setId(emailAlertEntity.getId());
		emailAlertModel.setToAddress(emailAlertEntity.getToAddress());
		emailAlertModel.setMailSubject(emailAlertEntity.getMailSubject());
		emailAlertModel.setMailBody(emailAlertEntity.getMailBody());
		emailAlertModel.setEmailSent(emailAlertEntity.isEmailSent());
		return emailAlertModel;
	}

	private EmailAlert convertModelToEntity(com.snsystems.emailAlerts.models.EmailAlert emailAlertModel) {
		com.snsystems.emailAlerts.entities.EmailAlert emailAlertEntity = new com.snsystems.emailAlerts.entities.EmailAlert();
		emailAlertEntity.setId(emailAlertModel.getId());
		emailAlertEntity.setToAddress(emailAlertModel.getToAddress());
		emailAlertEntity.setMailSubject(emailAlertModel.getMailSubject());
		emailAlertEntity.setMailBody(emailAlertModel.getMailBody());
		emailAlertEntity.setEmailSent(emailAlertModel.isEmailSent());

		return emailAlertEntity;
	}	
}
