package com.vinan.javatestproblem.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class DatCustomerDto {
	private Integer id;
    private String fullName;
	private String email;
	private String address;
	private String kdDati2;
	private String kotaKabupaten;


}
