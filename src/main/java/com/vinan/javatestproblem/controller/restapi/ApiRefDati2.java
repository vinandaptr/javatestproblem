package com.vinan.javatestproblem.controller.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinan.javatestproblem.model.entity.RefDati2;
import com.vinan.javatestproblem.repository.RefDati2Repository;

@RestController
@RequestMapping("/api/refdati2")
public class ApiRefDati2 {
	
	@Autowired
	private RefDati2Repository refDati2Repository;

    @GetMapping("/get-all")
    public List<RefDati2> getAll() {

        return refDati2Repository.findAll();
    }

    @PostMapping
    public RefDati2 save(@RequestBody RefDati2 refDati2) {

        return refDati2Repository.save(refDati2);
    }
    
    @GetMapping("/get-all-kota")
    public List<RefDati2> getAllKota() {

        return refDati2Repository.findAll();
    }

    @GetMapping("/{kdDati2}")
    public RefDati2 getByKdDati2(@PathVariable String kdDati2) {
        return refDati2Repository.findAllByKdDati2(kdDati2);
    }
	
}
