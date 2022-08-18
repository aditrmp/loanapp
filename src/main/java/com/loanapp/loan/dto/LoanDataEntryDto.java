package com.loanapp.loan.dto;

import lombok.Data;

@Data
public class LoanDataEntryDto {
	private String userId;
	private String productCode;
	private long creditApplicationNominal;
}
