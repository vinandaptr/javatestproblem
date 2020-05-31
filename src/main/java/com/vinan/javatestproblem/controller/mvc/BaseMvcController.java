package com.vinan.javatestproblem.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class BaseMvcController {
	
	@GetMapping("customer")
	public String customer() {
		return "customer/index";
	}
	
	@GetMapping("daftarkota")
	public String daftarkota() {
		return "customer/daftarkota";
	}

}
