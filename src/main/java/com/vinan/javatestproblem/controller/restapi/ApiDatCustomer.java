package com.vinan.javatestproblem.controller.restapi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinan.javatestproblem.model.dto.DatCustomerDto;
import com.vinan.javatestproblem.model.entity.DatCustomer;
import com.vinan.javatestproblem.model.entity.RefDati2;
//import com.vinan.javatestproblem.model.entity.RefDati2;
import com.vinan.javatestproblem.repository.DatCustomerRepository;
import com.vinan.javatestproblem.repository.RefDati2Repository;
//import com.vinan.javatestproblem.repository.RefDati2Repository;
import com.vinan.javatestproblem.service.DatCustomerService;

@RestController
@RequestMapping("/api/customer")
public class ApiDatCustomer {
	@Autowired
	private DatCustomerRepository datCustomerRepository;
	
	@Autowired
	private RefDati2Repository refDati2Repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private DatCustomerService datCustomerService;
	
	
//	@GetMapping
//	public List<DatCustomerDto> getListDatCustomer() {
//        List<DatCustomer> datCustomerList = datCustomerRepository.findAll();
//        List<DatCustomerDto> datCustomerDtos =
//                datCustomerList.stream()
//                        .map(datCustomer -> setDatCustomerDto(datCustomer))
//                        .collect(Collectors.toList());
//        
//        return datCustomerDtos;
//    }
//	
//	private DatCustomerDto setDatCustomerDto(DatCustomer datCustomer) {
//		DatCustomerDto bd = modelMapper.map(datCustomer, DatCustomerDto.class);
//		if(datCustomer.getRefDati2()!=null) {
//			modelMapper.map(datCustomer.getRefDati2(), bd);
//		}
//				
//		return bd;
//	}
	
	@GetMapping()
	  public List<DatCustomer> getDatCustomer() {
	    return datCustomerRepository.findAll();
	  }
	
	@GetMapping("/get-all")
	  public List<DatCustomerDto> getListDatCustomer(){
	    List<DatCustomer> datCustomerList= datCustomerRepository.findAll();
	    List<DatCustomerDto> datCustomerDtoList = new ArrayList<DatCustomerDto>();
	    for (DatCustomer datCustomer : datCustomerList) {
	      DatCustomerDto datCustomerDto = new DatCustomerDto();
	      modelMapper.map(datCustomer, datCustomerDto);
	      datCustomerDto.setKdDati2(datCustomer.getRefDati2().getKdDati2());
	      datCustomerDto.setKotaKabupaten(datCustomer.getRefDati2().getKotaKabupaten());
	      datCustomerDtoList.add(datCustomerDto);
	    }
	    return datCustomerDtoList;
	  }
	
	@GetMapping("/{idAja}")
	public DatCustomerDto getDatCustomer(@PathVariable Integer idAja) {
		DatCustomer datCustomer = datCustomerRepository.findById(idAja).get();
		DatCustomerDto datCustomerDto = new DatCustomerDto();
		datCustomerDto.setId(datCustomer.getId());
		datCustomerDto.setFullName(datCustomer.getFullName());
		datCustomerDto.setEmail(datCustomer.getEmail());
		datCustomerDto.setAddress(datCustomer.getAddress());
		
		datCustomerDto.setKdDati2(datCustomer.getRefDati2().getKdDati2());
		datCustomerDto.setKotaKabupaten(datCustomer.getRefDati2().getKotaKabupaten());
		
		return datCustomerDto;

	}
	
	@PostMapping
	public DatCustomerDto editSaveDatCustomer(@RequestBody DatCustomerDto datCustomerDto) {
		DatCustomer dc = new DatCustomer();
		RefDati2 rd = new RefDati2();
		rd.setKdDati2(datCustomerDto.getKdDati2());
		RefDati2 rd2 = refDati2Repository.findAllByKdDati2(datCustomerDto.getKdDati2());
		rd.setKotaKabupaten(rd2.getKotaKabupaten());
		
		refDati2Repository.save(rd);
		
		dc.setId(datCustomerDto.getId());
	    dc.setFullName(datCustomerDto.getFullName());
	    dc.setEmail(datCustomerDto.getEmail());
		dc.setAddress(datCustomerDto.getAddress());
	    dc.setRefDati2(rd);
	    
	    DatCustomer dc2 = datCustomerRepository.save(dc);
	    datCustomerDto.setId(dc2.getId());
		
		return datCustomerDto;
	}
	
	@DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        datCustomerRepository.deleteById(Integer.parseInt(id));
    }
	
	@GetMapping("/transaksi")
    public DatCustomer latTransactional() {
        DatCustomer datCustomer = datCustomerService.latTransactional();

        return datCustomer;
    }
	
//	@PutMapping("/{id}")
//	public DatCustomer<DatCustomerDto> updateDatCustomer(@PathVariable Integer id,
//			@RequestBody DatCustomerDto datCustomerDto) {
//		
//		DatCustomer customer = datCustomerRepository.findById(id);
//				customer.setId(datCustomerDto.getId());
//				customer.setFullName(datCustomerDto.getFullName());
//				customer.setEmail(datCustomerDto.getEmail());
//				customer.setAddress(datCustomerDto.getAddress());
//				
//		DatCustomer updatedCustomer = datCustomerRepository.save(customer);
//		
//		return DatCustomer.save(updatedCustomer);
//	}
	
//	@PutMapping("/customer/{id}")
//	public ResponseEntity<DatCustomer> updateDatCustomer(@PathVariable Integer id,
//	  @Valid @RequestBody DatCustomer datCustomer) throws ResourceNotFoundException {
//	 DatCustomer customer = datCustomerRepository.findById(id)
//	 .orElseThrow(() => new ResourceNotFoundException("Employee not found for this id : " + id));
//
//		 customer.setId(datCustomer.getId());
//		 customer.setFullName(datCustomer.getFullName());
//		 customer.setEmail(datCustomer.getEmail());
//		 customer.setAddress(datCustomer.getAddress());
//		 
//		 final DatCustomer updatedCustomer = datCustomerRepository.save(customer);
//		 return ResponseEntity.ok(updatedCustomer);
//	}

}
