package com.loanapp.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.loanapp.loan.models.MasterNasabah;

public interface MasterNasabahRepository extends JpaRepository<MasterNasabah, Integer>{
	@Query(value = "SELECT * from master_nasabah WHERE nik = :nik and is_active=1",
			nativeQuery = true)
	MasterNasabah findNasabahByNik(
				@Param("nik") String nik
				) ;
	
	
}
