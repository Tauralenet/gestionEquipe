package com.fr.adaming.service;

import java.util.List;


import com.fr.adaming.entity.User;

public interface IUserService {
	
	public User create(User entity);
	
	public User update(User entity);

	public List<User> findAll();

	public User findById(Long id);

	public boolean deleteById(Long id);

}
