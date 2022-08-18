package com.loanapp.loan.controller;

import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.loanapp.loan.dto.ScoringDataDto;
import com.loanapp.loan.models.MasterLoan;
import com.loanapp.loan.models.MasterNasabah;
import com.loanapp.loan.models.MasterParameter;
import com.loanapp.loan.models.ScoringHistory;
import com.loanapp.loan.services.MasterLoanServices;
import com.loanapp.loan.services.MasterNasabahServices;
import com.loanapp.loan.services.MasterParameterServices;
import com.loanapp.loan.services.ScoringHistoryServices;

@RestController
@RequestMapping("/scoring")
public class ScoringController {
	@Autowired
	private MasterParameterServices masterParameterServices;

	@Autowired
	private MasterLoanServices loanServices;
	
	@Autowired
	private MasterNasabahServices nasabahServices;

	@Autowired
	private ScoringHistoryServices scoringHistoryServices;
	
	private List<MasterParameter> allParam;
	
	@RequestMapping(value = "/setscore", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<?> dataEntry(@RequestBody ScoringDataDto req) throws URISyntaxException {
		try {
			if(req != null) {
				MasterLoan loanData = loanServices.getSubmittedLoan(Integer.valueOf(req.getLoanId()));
				MasterNasabah nasabahData = nasabahServices.findNasabahById(Integer.valueOf(req.getUserId()));;
				Date date = new Date();

				if(loanData == null)
					return new ResponseEntity<>(
							"No Loan data found", HttpStatus.OK);

				if(nasabahData == null)
					return new ResponseEntity<>(
							"No Nasabah data found", HttpStatus.OK);

				getAllParam();						
				
				double maxUP = 10000000;
				
				Optional<MasterParameter> interestParam = allParam.stream().filter(s -> s.getLabel().equals("PERSEN_BUNGA") && s.getName().equals("BUNGA")).findFirst();
				Optional<MasterParameter> operationalParam = allParam.stream().filter(s -> s.getLabel().equals("OPERATIONAL") && s.getName().equals("BUNGA")).findFirst();
				
				double interest = Double.valueOf(interestParam.get().getValue());
				double operational = Double.valueOf(operationalParam.get().getValue());
				
				double totalInterest = interest + operational;
				
				double pengajuan = Double.valueOf(loanData.getCreditApplicationNominal()); 
				double angsuran = (pengajuan + (pengajuan * totalInterest /100)) / 12;
				double rasioPendapatanAngsuran = angsuran / Double.valueOf(nasabahData.getMonthlyIncome());
				int idRasioPendapatanAngsuran = 0;
				
				if(rasioPendapatanAngsuran < 0.3)
					idRasioPendapatanAngsuran = 16;
				else if(rasioPendapatanAngsuran > 0.3 && rasioPendapatanAngsuran < 0.5)
					idRasioPendapatanAngsuran = 16;
				else
					idRasioPendapatanAngsuran = 14;
				
				final int rasioPA = idRasioPendapatanAngsuran;
				
				int age = 0;
				int ageCategory = 0;
				age = date.getYear() - nasabahData.getDob().getYear();
				if(age > 16 && age < 26)
					ageCategory = 21;
				else if(age > 25 && age < 36)
					ageCategory = 22;
				else if(age > 35 && age < 65)
					ageCategory = 23;
				else if(age > 64)
					ageCategory = 25;
				final int ageCat = ageCategory;
				
				Optional<MasterParameter> paramRasioJenisPekerjaan = allParam.stream().filter(s -> s.getLabel().equals("JENIS_PEKERJAAN") && s.getName().equals("RASIO_TOTAL")).findFirst();
				Optional<MasterParameter> paramRasioLamaKerja = allParam.stream().filter(s -> s.getLabel().equals("LAMA_KERJA") && s.getName().equals("RASIO_TOTAL")).findFirst();
				Optional<MasterParameter> paramRasioGajiAngsuran = allParam.stream().filter(s -> s.getLabel().equals("PERSEN_GAJI_ANGSURAN") && s.getName().equals("RASIO_TOTAL")).findFirst();
				Optional<MasterParameter> paramRasioStatusNikah = allParam.stream().filter(s -> s.getLabel().equals("STATUS_PERNIKAHAN") && s.getName().equals("RASIO_TOTAL")).findFirst();
				Optional<MasterParameter> paramRasioUsia = allParam.stream().filter(s -> s.getLabel().equals("USIA") && s.getName().equals("RASIO_TOTAL")).findFirst();
				
//				Optional<MasterParameter> paramJenisPekerjaan = allParam.stream().filter(s -> s.getId() == req.getOccupation()).findFirst();
//				Optional<MasterParameter> paramLamaKerja = allParam.stream().filter(s -> s.getId() == req.getOccupationLong()).findFirst();
//				Optional<MasterParameter> paramGajiAnhgsuran = allParam.stream().filter(s -> s.getId() == req.getSalary()).findFirst();
//				Optional<MasterParameter> paramStatusNikah = allParam.stream().filter(s -> s.getId() == req.getMarriageStatus()).findFirst();
//				Optional<MasterParameter> paramUsia = allParam.stream().filter(s -> s.getId() == req.getAge()).findFirst();

				Optional<MasterParameter> paramJenisPekerjaan = allParam.stream().filter(s -> s.getId() == nasabahData.getOccupation()).findFirst();
				Optional<MasterParameter> paramLamaKerja = allParam.stream().filter(s -> s.getId() == nasabahData.getOccupationLong()).findFirst();
				Optional<MasterParameter> paramGajiAngsuran = allParam.stream().filter(s -> s.getId() == rasioPA).findFirst();
				Optional<MasterParameter> paramStatusNikah = allParam.stream().filter(s -> s.getId() == req.getMarriageStatus()).findFirst();
				Optional<MasterParameter> paramUsia = allParam.stream().filter(s -> s.getId() == ageCat).findFirst();
				
				double scoreJenisPekerjaan = (Double.parseDouble(paramRasioJenisPekerjaan.get().getValue()) / 100) *  Double.parseDouble(paramJenisPekerjaan.get().getValue());
				double scoreLamaKerja = (Double.parseDouble(paramRasioLamaKerja.get().getValue()) / 100) *  Double.parseDouble(paramLamaKerja.get().getValue());
				double scoreGajiAnhgsuran = (Double.parseDouble(paramRasioGajiAngsuran.get().getValue()) / 100) *  Double.parseDouble(paramGajiAngsuran.get().getValue());
				double scoreStatusNikah = (Double.parseDouble(paramRasioStatusNikah.get().getValue()) / 100) *  Double.parseDouble(paramStatusNikah.get().getValue());
				double scoreUsia = (Double.parseDouble(paramRasioUsia.get().getValue()) / 100) *  Double.parseDouble(paramUsia.get().getValue());
				
				double scoreSum = scoreJenisPekerjaan + scoreLamaKerja + scoreGajiAnhgsuran + scoreStatusNikah + scoreUsia;
				double finalScore = scoreSum / 5;
				
				double maxPlafond = 0;
				if(finalScore > 0.8)
					maxPlafond = loanData.getCreditApplicationNominal();
				else if(finalScore < 0.6)
					return new ResponseEntity<>(
							"rejected", HttpStatus.OK);
				else {
					maxPlafond = maxUP * finalScore;
				}
				
				MasterLoan item =  new MasterLoan();
				item = loanData;
				item.setCreditApprovedNominal(Long.valueOf(String.valueOf(Math.round(maxPlafond))));
				item.setWorkflowStatus("DISBU");
				item.setUpdatedDate(date);
				loanServices.saveLoan(item);
				
				ScoringHistory scoreHist = new ScoringHistory();
				scoreHist.setLoanId(loanData.getId());
				scoreHist.setProductCode(loanData.getProductCode());
				scoreHist.setOccupation(nasabahData.getOccupation());
				scoreHist.setOccupationLong(nasabahData.getOccupationLong());
				scoreHist.setSalary(rasioPA);
				scoreHist.setMarriageStatus(nasabahData.getMariageStatus());
				scoreHist.setAgeCategory(ageCat);
				scoreHist.setScore(finalScore);
				scoreHist.setIsActive(1);
				scoringHistoryServices.save(scoreHist);

//				return new ResponseEntity<>(
//						finalScore + " " + maxPlafond , HttpStatus.OK);

				return new ResponseEntity<>(
						"data saved" , HttpStatus.OK);
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
	
	private void getAllParam(){
		allParam = masterParameterServices.getAllParam();
		
	}
	
	
}
