package com.loanapp.loan.services;

import org.springframework.stereotype.Service;

import com.loanapp.loan.models.ScoringHistory;
import com.loanapp.loan.repository.ScoringHistoryRepository;

@Service
public class ScoringHistoryServices {
	private ScoringHistoryRepository scoringHistoryRepository;
	
	public ScoringHistoryServices(ScoringHistoryRepository scoringHistoryRepository) {
		this.scoringHistoryRepository = scoringHistoryRepository;
	}
	
	public ScoringHistory findAcitveScore(long loanId) {
		ScoringHistory data = scoringHistoryRepository.findAcitveScore(loanId);
		
		return data;		
	}
	
	public void save(ScoringHistory hist) {
		scoringHistoryRepository.save(hist);
		
	}
}
