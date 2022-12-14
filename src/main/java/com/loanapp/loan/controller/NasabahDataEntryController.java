package com.loanapp.loan.controller;

import java.net.URISyntaxException;
import java.util.Date;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.loanapp.loan.dto.NasabahDataEntryDto;
import com.loanapp.loan.models.MasterNasabah;
import com.loanapp.loan.services.MasterNasabahServices;

@RestController
@RequestMapping("/nasabah")
public class NasabahDataEntryController {
	@Autowired
	private MasterNasabahServices nasabahService;
	
	@RequestMapping(value = "/nasabahdataentry", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<?> dataEntry(@RequestBody NasabahDataEntryDto req) throws URISyntaxException {
		try {
			if(req != null) {
				MasterNasabah nasabahData = nasabahService.findNasabahByNik(req.getNik(), req.getEmail());
				MasterNasabah otherNasabahData = nasabahService.findNasabahByOtherNik(req.getNik(), req.getEmail());

				Date date = new Date();
				
//				if(nasabahData == null) {
//					MasterNasabah item = new MasterNasabah();
////					if(nasabahData != null) {
////						item.setId(nasabahData.getId());
////					}
//					item.setNik(req.getNik());
//					item.setNoKK(req.getNoKK());
//					item.setCustomerName(req.getCustomerName());
//					item.setDob(req.getDob());
//					item.setMotherName(req.getMotherName());
//					item.setGender(req.getGender());
//					item.setNationality(req.getNationality());
//					item.setPhoneNumber(req.getPhoneNumber());
//					item.setOccupation(req.getOccupation());
//					item.setIsActive(1);
//					
//					nasabahService.saveNasabah(item); 
//	
//					return new ResponseEntity<>(
//							item, HttpStatus.OK);
//				}else {
//					return new ResponseEntity<>(
//							"NIK Already exist.", HttpStatus.ACCEPTED);
//				}

				if(otherNasabahData != null)
					return new ResponseEntity<>(
							"NIK Already exist.", HttpStatus.ACCEPTED);
							
				MasterNasabah item = new MasterNasabah();
				if(nasabahData != null) {
					item.setId(nasabahData.getId());
					item.setUpdatedDate(date);
				}
				item.setNik(req.getNik());
				item.setNoKK(req.getNoKK());
				item.setCustomerName(req.getCustomerName());
				item.setDob(req.getDob());
				item.setMotherName(req.getMotherName());
				item.setGender(req.getGender());
				item.setNationality(req.getNationality());
				item.setPhoneNumber(req.getPhoneNumber());
				item.setMariageStatus(Long.parseLong(req.getMariageStatus()));
				item.setOccupation(Long.parseLong(req.getOccupation()));
				item.setOccupationLong(Long.parseLong(req.getOccupationLong()));
				item.setMonthlyIncome(Long.parseLong(req.getMonthlyIncome()));
				item.setEmail(req.getEmail());
				item.setCreatedDate(nasabahData.getCreatedDate());
				item.setIsActive(1);
				
				nasabahService.saveNasabah(item); 

				return new ResponseEntity<>(
						item, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(
						"Request is null", HttpStatus.ACCEPTED);
			} 
			
		} catch (HibernateException e) {
			System.out.println(e);
			return new ResponseEntity<>(
					"Error", HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(value = "/nasabahdataget", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<?> dataGet(String nik, String email) throws URISyntaxException {
		try {
			if(nik != null) {
				MasterNasabah nasabahData = nasabahService.findNasabahByNik(nik, email);
				if(nasabahData == null) {
					return new ResponseEntity<>(
							"User Not found.", HttpStatus.ACCEPTED);
				}
				
				return new ResponseEntity<>(
						nasabahData, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(
						"Request is null", HttpStatus.ACCEPTED);
			} 
			
		} catch (HibernateException e) {
			System.out.println(e);
			return new ResponseEntity<>(
					"Error", HttpStatus.CONFLICT);
		}
	}
}
