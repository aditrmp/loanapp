package com.loanapp.loan.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "master_parameter")
@Data
public class MasterParameter extends DataDetail{
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "name", nullable = true)
	private String name;
	
	@Column(name = "label", nullable = true)
	private String label;

	@Column(name = "value", nullable = true)
	private String value;

}
