package com.loanapp.loan.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * Loan Master Data 
 * This loan data record 
 * @author aditramp
 *
 */

@Entity
@Table(name = "master_loan")
@Data
public class MasterLoan extends DataDetail{
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "cif", nullable = true)
	private String cif;
	
	@Column(name = "user_id", nullable = false)
	private String userId;
	
	@Column(name = "workflow_status", nullable = true)
	private String workflowStatus;
	
	@Column(name = "product_code", nullable = true)
	private String productCode;
	
	@Column(name = "application_date", nullable = true)
	private Date applicationDate;

	@Column(name = "credit_application_nominal", nullable = true)
	private long creditApplicationNominal;

	@Column(name = "credit_approved_nominal", nullable = true)
	private long creditApprovedNominal;
	
	@Column(name = "credit_disbursed_nominal", nullable = true)
	private long creditDisbursedNominal;
	
	@Column(name = "disbursement_date", nullable = true)
	private Date disbursementDate;
	
	@Column(name = "is_submitted", nullable = true)
   	private int isSubmitted ;
	
    /**
   	 * Indicate is user active or not
   	 */
   	@Column(name = "is_active", nullable = false)
   	private int isActive ;
}
