package com.loanapp.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.loanapp.loan.models.MasterNasabah;

public interface MasterNasabahRepository extends JpaRepository<MasterNasabah, Integer>{
	@Query(value = "SELECT * from master_nasabah WHERE nik = :nik and email = :email and is_active=1",
			nativeQuery = true)
	MasterNasabah findNasabahByNik(
				@Param("nik") String nik,
				@Param("email") String email
				) ;
	
	@Query(value = "SELECT * from master_nasabah WHERE nik = :nik and email != :email and is_active=1",
			nativeQuery = true)
	MasterNasabah findNasabahByOtherNik(
				@Param("nik") String nik,
				@Param("email") String email
				) ;

	
	@Query(value = "SELECT * from master_nasabah WHERE id = :id and is_active=1",
			nativeQuery = true)
	MasterNasabah findNasabahById(
				@Param("id") long id
				) ;
}
