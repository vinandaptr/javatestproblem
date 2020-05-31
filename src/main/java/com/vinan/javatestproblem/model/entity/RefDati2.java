package com.vinan.javatestproblem.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;

@Entity
@Data
@Table(name = RefDati2.TABLE_NAME)
public class RefDati2 {
	public static final String TABLE_NAME = "ref_dati2";
	
	@Id
    @GeneratedValue(generator = "sequence_generator")
	@GenericGenerator(
			name = "sequence_generator", 
			strategy = "com.vinan.javatestproblem.generator.GeneratorRefDati2",
			parameters = { @Parameter(name = "prefix", value = "rd") } )
	@Column(name = "kd_dati2", length = 4, nullable = false)
	private String kdDati2;
	
	@Column(name = "nm_dati2", length = 128, nullable = false)
	private String kotaKabupaten;
	
}
