package com.loanapp.loan.dto;

import java.util.Date;

import lombok.Data;

@Data
public class NasabahDataEntryDto {
	private String nik;
	private String noKK;
	private String customerName;
	private Date dob;
	private String motherName;
	private String gender;
	private String nationality;
	private String phoneNumber;
	private String mariageStatus;
	private String occupation;
	private String occupationLong;	
	private String monthlyIncome;
	private String email;
}
