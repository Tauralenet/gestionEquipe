package com.fr.adaming.rest;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.User;
import com.fr.adaming.service.IUserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/api")
public class UserRestController {

	@Autowired
	private IUserService service;
	
	@RequestMapping(path="/user", method= RequestMethod.POST)
	public User create(@RequestBody User user){
			 return service.create(user);
	}
	
	@RequestMapping(path="/user", method= RequestMethod.GET)
	public List<User> readAll(){
		return service.findAll();
	}
	
	@RequestMapping(path="/user/readById/{idReq}", method= RequestMethod.GET)
	public User readById(@PathVariable ("idReq") Long id){
		return service.findById(id);
	}
	
	@RequestMapping(path="/user", method= RequestMethod.PUT)
	public void update(@RequestBody User user){
			System.out.println(user);
			service.update(user);
	}
	
	
	@RequestMapping(path="/user/{idReq}", method= RequestMethod.DELETE)
	public void delete(@PathVariable ("idReq") Long id){
		 service.deleteById(id);
	}

}
