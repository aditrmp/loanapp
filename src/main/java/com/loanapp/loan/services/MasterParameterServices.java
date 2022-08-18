package com.loanapp.loan.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.loanapp.loan.models.MasterParameter;
import com.loanapp.loan.repository.MasterParameterRepository;

@Service
public class MasterParameterServices {
	private MasterParameterRepository masterParameterRepository;
	
	public MasterParameterServices (MasterParameterRepository masterParameterRepository) {
		this.masterParameterRepository = masterParameterRepository;
	}
	
//	public MasterParameter findParam(String name, String label) {
//		MasterParameter data = new MasterParameter();
//		data = masterParameterRepository.findParam(name, label);
//		
//		return data;
//	}

	public MasterParameter findParamByNameLabel(String name, String label) {
		MasterParameter data = new MasterParameter();
		data = masterParameterRepository.findParamByNameLabel(name, label);
		
		return data;
	}
	
	
	public List<MasterParameter> getAllParam(){
		List<MasterParameter> allParam = null;
		allParam = masterParameterRepository.findAll();
		
		return allParam;
	}
}
