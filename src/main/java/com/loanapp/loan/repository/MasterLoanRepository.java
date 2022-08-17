package com.loanapp.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.loanapp.loan.models.MasterLoan;

public interface MasterLoanRepository extends JpaRepository<MasterLoan, Integer>{
	@Query(value = "SELECT * from master_loan WHERE user_id = :userId and is_submitted = 0 and is_active = 1",
			nativeQuery = true)
	MasterLoan findIncompleteApplicationLoan(
			@Param("userId") String userId
	) ;
	
	@Query(value = "SELECT * from master_loan WHERE user_id = :userId and is_submitted = 1 and is_active = 1",
			nativeQuery = true)
	MasterLoan findSubmittedApplicationLoan(
			@Param("userId") String userId
	) ;
}
