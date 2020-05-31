package com.vinan.javatestproblem.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinan.javatestproblem.model.entity.DatCustomer;
import com.vinan.javatestproblem.repository.DatCustomerRepository;
import com.vinan.javatestproblem.repository.RefDati2Repository;

@Transactional
@Service
public class DatCustomerServiceImpl implements DatCustomerService {
	@Autowired
	private DatCustomerRepository datCustomerRepository;
	
	@Autowired
	private RefDati2Repository refDati2Repository;
	
	@Override
	public DatCustomer latTransactional() {
		DatCustomer datCustomer = datCustomerRepository.findById(6).get();
		datCustomer.setFullName("perubahan 1");
		datCustomerRepository.save(datCustomer);
		
		Integer.parseInt("errorword");
		
		datCustomer.setFullName("perubahan 2");
		
		datCustomerRepository.save(datCustomer);

		return datCustomer;		
		
	}
	
	@Override
	public DatCustomer saveDatCustomerMaterRefDati2(DatCustomer datCustomer) {
		
		refDati2Repository.save(datCustomer.getRefDati2());
		
		datCustomerRepository.save(datCustomer);
		
		return datCustomer;
	}
	

}
