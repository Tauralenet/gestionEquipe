package com.fr.adaming.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.Equipe;
import com.fr.adaming.service.EquipeService;

@RestController
@RequestMapping(path = "/api")
public class EquipeRestController {

	@Autowired
	private EquipeService eqService;
	
	@RequestMapping(path="/equipe", method= RequestMethod.POST)
	public void create(@RequestBody Equipe equipe){
		System.out.println("DEBUG equipe"+equipe);
		eqService.create(equipe);
	}
	
	@RequestMapping(path="/equipe", method= RequestMethod.GET)
	public List<Equipe> readAll(){
		return eqService.findAll();
	}
	
	@RequestMapping(path="/equipe/readById/{idReq}", method= RequestMethod.GET)
	public Equipe readById(@PathVariable ("idReq") Long id){
		return eqService.findById(id);
	}
	
	@RequestMapping(path="/equipe", method= RequestMethod.PUT)
	public void update(@RequestBody Equipe equipe){
		System.out.println("DEBUG equipe"+equipe);
		eqService.update(equipe);
	}
	
	@RequestMapping(path="/equipe/{idReq}", method= RequestMethod.DELETE)
	public void delete(@PathVariable ("idReq") Long id){
		 eqService.deleteById(id);
	}
	
}
