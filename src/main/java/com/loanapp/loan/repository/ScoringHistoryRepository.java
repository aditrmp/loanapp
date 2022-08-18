package com.loanapp.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.loanapp.loan.models.ScoringHistory;

public interface ScoringHistoryRepository extends JpaRepository<ScoringHistory, Integer>{
	@Query(value = "SELECT * from master_parameter WHERE loan_id = :loanid and is_active = 1",
			nativeQuery = true)
	ScoringHistory findAcitveScore(
			@Param("loanid") long loanId
	) ;
}
