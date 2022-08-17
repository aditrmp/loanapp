package com.loanapp.loan.services;

import org.springframework.stereotype.Service;

import com.loanapp.loan.models.MasterLoan;
import com.loanapp.loan.repository.MasterLoanRepository;

@Service
public class MasterLoanServices {
	private MasterLoanRepository masterLoanRepository;
	
	public MasterLoanServices (MasterLoanRepository masterLoanRepository) {
		this.masterLoanRepository = masterLoanRepository;
	}
	
	public MasterLoan getUnsubmittedLoan(String userId) {
		MasterLoan data = masterLoanRepository.findIncompleteApplicationLoan(userId);
		
		return data;
	}

	public MasterLoan getSubmittedLoan(String userId) {
		MasterLoan data = masterLoanRepository.findSubmittedApplicationLoan(userId);
		
		return data;
	}
	
	public void saveLoan(MasterLoan data) {
		masterLoanRepository.save(data);
	}
	
}
