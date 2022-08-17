package com.loanapp.loan.services;

import org.springframework.stereotype.Service;

import com.loanapp.loan.models.MasterNasabah;
import com.loanapp.loan.repository.MasterNasabahRepository;

@Service
public class MasterNasabahServices {
	private MasterNasabahRepository masterNasabahRepository;
	
	public MasterNasabahServices (MasterNasabahRepository masterNasabahRepository) {
		this.masterNasabahRepository = masterNasabahRepository;
	}
	
	public MasterNasabah findNasabahByNik(String nik) {
		MasterNasabah nasabahdata = masterNasabahRepository.findNasabahByNik(nik);
		return nasabahdata;
	}
	
	public void saveNasabah(MasterNasabah data) {
		masterNasabahRepository.save(data);
	}
}
