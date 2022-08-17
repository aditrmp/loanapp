package com.loanapp.loan.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "user_app_nasabah_map")
@Data
public class UserAppNasabahMap extends DataDetail{
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "account_id", nullable = false)
	private String accountId;
}
