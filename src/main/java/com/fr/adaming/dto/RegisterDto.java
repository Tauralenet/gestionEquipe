package com.fr.adaming.dto;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fr.adaming.entity.Equipe;

import lombok.Data;

@Data
public class RegisterDto {

	@NotNull
	private String nom;
	
	@Email
	@NotNull
	private String email;
	
	@NotNull
	private String pwd;
	
	@NotNull
	private Equipe joueurs; 
}
