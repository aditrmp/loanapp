package com.loanapp.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.loanapp.loan.models.MasterParameter;

public interface MasterParameterRepository extends JpaRepository<MasterParameter, Integer>{
	@Query(value = "SELECT * from master_parameter WHERE name = :name and label = :label",
			nativeQuery = true)
	MasterParameter findParamByNameLabel(
			@Param("name") String name,
			@Param("label") String label
	) ;
	
	@Query(value = "SELECT * from master_parameter WHERE id = :id",
			nativeQuery = true)
	MasterParameter findParamById(
			@Param("id") long id
	) ;
}
