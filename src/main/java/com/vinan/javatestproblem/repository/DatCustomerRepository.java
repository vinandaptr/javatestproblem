package com.vinan.javatestproblem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinan.javatestproblem.model.entity.DatCustomer;

@Repository
public interface DatCustomerRepository extends JpaRepository<DatCustomer, Integer> {

}
