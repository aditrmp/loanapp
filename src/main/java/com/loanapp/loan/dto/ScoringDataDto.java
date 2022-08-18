package com.loanapp.loan.dto;

import lombok.Data;

@Data
public class ScoringDataDto {
	private long occupation;
	private long occupationLong;
	private long salary;
	private long marriageStatus;
	private long age;
	private String loanId;
	private String userId;
}
