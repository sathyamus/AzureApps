package com.snsystems.emailAlerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snsystems.emailAlerts.models.EmailAlert;
import com.snsystems.emailAlerts.service.EmailAlertService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/emailAlerts")
@Slf4j
@Api(value="/api/v1/emailAlerts", tags="EmailAlert operations")
public class EmailAlertController {

	@Autowired
	private EmailAlertService emailAlertService;

	@ApiOperation(value = "Fetches list of All EmailAlerts", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Fetched emailAlerts successfully")
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EmailAlert> findEmailAlerts() {
		log.info("Retrieving emailAlerts");
		return emailAlertService.findAll();
	}

	// TODO : Revisit API URI and service call
	@GetMapping(value = "/pkId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EmailAlert findByEmailAlertId(@PathVariable int id) {
		log.info("Retrieving emailAlert id : {}", id);
		return emailAlertService.findById(id);
	}
	
	@GetMapping(value = "/active", produces = MediaType.APPLICATION_JSON_VALUE)
	public EmailAlert findActiveEmailAlert() throws Exception {
		log.info("Retrieving Active emailAlert ");
		List<EmailAlert> emailAlerts = emailAlertService.findAll();
		if (CollectionUtils.isEmpty(emailAlerts)) {
			throw new Exception(HttpStatus.NO_CONTENT.getReasonPhrase());
		}
		return emailAlerts.get(0);
	}
	
	@GetMapping(value = "/{toAddress}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EmailAlert findByEmailAlertName(@PathVariable String toAddress) {
		log.info("Retrieving emailAlert by ToAddress : {}", toAddress);
		return emailAlertService.findByToAddress(toAddress);
	}	

	@PostMapping(value="/add")
	public ResponseEntity<?> addEmailAlert(@RequestBody EmailAlert emailAlertModel) throws JsonProcessingException {
		log.info("Posting emailAlert Record to DB : {}", new ObjectMapper().writeValueAsString(emailAlertModel));
		EmailAlert emailAlert = emailAlertService.addEmailAlert(emailAlertModel);
		return new ResponseEntity<>(emailAlert, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean deleteByEmailAlertId(@PathVariable int id) {
		log.info("Deleting emailAlert id : {}", id);
		return emailAlertService.deleteById(id);
	}
}
