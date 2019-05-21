package com.fr.adaming.service;

import com.fr.adaming.dto.RegisterDto;
import com.fr.adaming.entity.Equipe;
import com.fr.adaming.entity.User;

public interface IAuthService {
	
	public void findByEmailAndPwd(String email, String pwd);

	public User save(String nom, String email, String pwd);
}
