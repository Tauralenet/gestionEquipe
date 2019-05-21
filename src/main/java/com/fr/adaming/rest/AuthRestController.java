package com.fr.adaming.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.LoginDto;
import com.fr.adaming.dto.RegisterDto;
import com.fr.adaming.entity.User;
import com.fr.adaming.service.IAuthService;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthRestController {

	@Autowired
	private IAuthService authServ;

	@RequestMapping(path="/login", method = RequestMethod.POST)
	public LoginDto login(@Valid @RequestBody LoginDto loginDto) {
		authServ.findByEmailAndPwd(loginDto.getEmail(), loginDto.getPwd());
		return loginDto;
	}
	// Method argument not valid fail
	
	@RequestMapping(path="/register", method = RequestMethod.POST)
	public User register(@Valid @RequestBody RegisterDto registerDto) {
		User u = authServ.save(registerDto.getNom(), registerDto.getEmail(), registerDto.getPwd());
		return u;
	}
	
}
