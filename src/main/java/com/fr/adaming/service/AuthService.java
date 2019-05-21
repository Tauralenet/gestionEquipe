package com.fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IAuthDao;
import com.fr.adaming.entity.Equipe;
import com.fr.adaming.entity.User;

@Service
public class AuthService implements IAuthService {

	@Autowired
	private IAuthDao authDao;
	
	public void findByEmailAndPwd(String email, String pwd) {
		authDao.findByEmailAndPwd(email, pwd);
	}
	
	public User save(String nom, String email, String pwd) {
		User u1= new User(null, nom, email, pwd, null);
		User u = authDao.save(u1);
		return u;
	}
}
