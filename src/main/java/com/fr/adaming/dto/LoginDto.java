package com.fr.adaming.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;

import lombok.Data;

@Data
public class LoginDto {

	@Email
	@NotNull
	private String email;
	
	@NotNull
	private String pwd;
}
