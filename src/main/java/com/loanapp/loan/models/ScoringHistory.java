package com.loanapp.loan.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "scoring_history")
@Data
public class ScoringHistory extends DataDetail{
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "loan_id", nullable = true)
	private long loanId;
	
	@Column(name = "product_code", nullable = true)
	private String productCode;

	@Column(name = "occupation", nullable = true)
	private long occupation;

	@Column(name = "occupation_long", nullable = true)
	private long occupationLong;

	@Column(name = "salary", nullable = true)
	private long salary;

	@Column(name = "marriage_status", nullable = true)
	private long marriageStatus;

	@Column(name = "age_category", nullable = true)
	private long ageCategory;
	
	@Column(name = "score", nullable = true)
	private Double score;

	@Column(name = "is_active", nullable = true)
	private int isActive;	
}
