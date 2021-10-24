package com.snsystems.emailAlerts.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.snsystems.emailAlerts.entities.EmailAlert;

@Repository("emailAlertRepository")
public interface EmailAlertRepository extends JpaRepository<EmailAlert, Integer> {

	List<EmailAlert> findByToAddress(String toAddress);

}