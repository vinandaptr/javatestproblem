package com.vinan.javatestproblem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vinan.javatestproblem.model.entity.RefDati2;

@Repository
public interface RefDati2Repository extends JpaRepository<RefDati2, String>{
	
	//@Query(value="select * from ref_dati2 a where a.kdDati2 = ?1", nativeQuery = true)
	RefDati2 findAllByKdDati2(String kdDati2);
	
//	List<RefDati2>
//	findAllByKdDati2(String kdDati2);
}
