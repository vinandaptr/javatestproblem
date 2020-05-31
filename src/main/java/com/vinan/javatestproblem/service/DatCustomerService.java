package com.vinan.javatestproblem.service;

import com.vinan.javatestproblem.model.entity.DatCustomer;

public interface DatCustomerService {
	DatCustomer latTransactional();
	
	DatCustomer saveDatCustomerMaterRefDati2(DatCustomer datCustomer);

}
