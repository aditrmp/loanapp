package com.loanapp.loan.controller;

import java.net.URISyntaxException;
import java.util.Date;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.loanapp.loan.dto.LoanDataEntryDto;
import com.loanapp.loan.models.MasterLoan;
import com.loanapp.loan.services.MasterLoanServices;

@RestController
@RequestMapping("/loan")
public class LoanDataEntryController {
	@Autowired
	private MasterLoanServices loanService;
	
	@RequestMapping(value = "/loandataentry", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<?> dataEntry(@RequestBody LoanDataEntryDto req) throws URISyntaxException {
		try {
			if(req != null) {
				MasterLoan loanData = loanService.getUnsubmittedLoan(req.getUserId());
				
				Date date = new Date();
				MasterLoan item = new MasterLoan();
				
				if(loanData != null) {
					item.setId(loanData.getId());
				}
				item.setUserId(req.getUserId());
				item.setWorkflowStatus("DATAENT");
				item.setProductCode(req.getProductCode());
				item.setCreditApplicationNominal(req.getCreditApplicationNominal());
				item.setIsSubmitted(0);
				item.setIsActive(1);
				
				loanService.saveLoan(item);

				return new ResponseEntity<>(
						item, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(
						"Request is null", HttpStatus.ACCEPTED);
			} 
			
		} catch (HibernateException e) {
			System.out.println(e);
			return new ResponseEntity<>(
					"Error", HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(value = "/getloandata", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<?> dataGet(String userId) throws URISyntaxException {
		try {
			if(userId != null) {
				MasterLoan loanData = loanService.getUnsubmittedLoan(userId);
				if(loanData == null) {
					return new ResponseEntity<>(
							"Loan data Not found.", HttpStatus.ACCEPTED);
				}
				
				return new ResponseEntity<>(
						loanData, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(
						"Request is null", HttpStatus.ACCEPTED);
			} 
			
		} catch (HibernateException e) {
			System.out.println(e);
			return new ResponseEntity<>(
					"Error", HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(value = "/submitloandata", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<?> submitApplication(String userId) throws URISyntaxException {
		try {
			if(userId != null) {
				Date date = new Date();
				
				MasterLoan loanData = loanService.getUnsubmittedLoan(userId);
				if(loanData == null) {
					return new ResponseEntity<>(
							"User do not have active application. Please create application first!", HttpStatus.ACCEPTED);
				}
				
//				loanData = loanService.getSubmittedLoan(userId);
				if(loanData.getApplicationDate() != null) {
					return new ResponseEntity<>(
							"User on loan offering status. Please accept or decline ", HttpStatus.ACCEPTED);
				
				}
				
				MasterLoan item = new MasterLoan();
				item = loanData;
				item.setId(loanData.getId());
				item.setApplicationDate(date);
				item.setWorkflowStatus("SCORE");
				item.setIsSubmitted(1);
				
				loanService.saveLoan(item);

				return new ResponseEntity<>(
						item, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(
						"Request is null", HttpStatus.ACCEPTED);
			} 
			
		} catch (HibernateException e) {
			System.out.println(e);
			return new ResponseEntity<>(
					"Error", HttpStatus.CONFLICT);
		}
	}
}
