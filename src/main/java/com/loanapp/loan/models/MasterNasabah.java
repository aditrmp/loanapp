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
 * Nasabah Master Data 
 * This customer data for general customer data 
 * @author aditramp
 *
 */

@Entity
@Table(name = "master_nasabah")
@Data
public class MasterNasabah extends DataDetail{
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "cif")
	private String cif;
	
	@Column(name = "nik", nullable = false)
	private String nik;
	
	@Column(name = "no_kk", nullable = false)
	private String noKK;
	
	@Column(name = "customer_name", nullable = false)
	private String customerName;
	
	@Column(name = "dob", nullable = false)
	private Date dob;
	
	@Column(name = "mother_name", nullable = false)
	private String motherName;

	@Column(name = "gender", nullable = false)
	private String gender;
	
	@Column(name = "nationality", nullable = true)
	private String nationality;

	@Column(name = "phone_number", nullable = true)
	private String phoneNumber;

	@Column(name = "mariage_status", nullable = false)
	private long mariageStatus;

	@Column(name = "occupation", nullable = false)
	private long occupation;

	@Column(name = "occupation_long", nullable = false)
	private long occupationLong;	
	
	@Column(name = "monthly_income", nullable = false)
	private long monthlyIncome;
	
	@Column(name = "email", nullable = true)
	private String email;
	
    /**
   	 * Indicate is user active or not
   	 */
   	@Column(name = "is_active", nullable = true)
   	private int isActive ;
   	
	public MasterNasabah() {
		
	}
}
