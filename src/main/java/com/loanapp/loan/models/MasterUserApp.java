package com.loanapp.loan.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "master_user_app")
@Data
public class MasterUserApp {
	@Id
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "nik", nullable = false)
	private String nik;

	@Column(name = "dob", nullable = false)
	private Date dob;
}
