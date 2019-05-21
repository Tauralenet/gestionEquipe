package com.fr.adaming.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.User;

@Repository
public interface IUserDao extends JpaRepository<User, Long> {

	public <S extends User> S save(S entity);

	public List<User> findAll();

	public Optional<User> findById(Long id);

	//public void deleteById(Long id);
	
}
