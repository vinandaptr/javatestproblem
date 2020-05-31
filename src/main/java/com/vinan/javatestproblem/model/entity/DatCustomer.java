package com.vinan.javatestproblem.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = DatCustomer.TABLE_NAME)
@Data
public class DatCustomer {
	public static final String TABLE_NAME = "dat_customer";

	@Id
	@GeneratedValue(generator = "sequence_cust", strategy = GenerationType.IDENTITY)
	@Column(name = "id_cust", length = 4, nullable = false)
	private Integer id;

	@Column(name = "full_name", length = 512, nullable = false)
	private String fullName;

	@Column(name = "email_address", length = 128, nullable = false)
	private String email;

	@Column(name = "address", length = 256, nullable = false)
	private String address;
 
	@ManyToOne
	//@JoinColumn(name = "kd_dati2", nullable = false, columnDefinition = "varchar(20)")
	private RefDati2 refDati2;

}
