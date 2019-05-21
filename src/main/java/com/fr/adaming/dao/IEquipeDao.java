package com.fr.adaming.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Equipe;

@Repository
public interface IEquipeDao extends JpaRepository<Equipe, Long> {
	
	public <S extends Equipe> S save(S entity);

	public List<Equipe> findAll();

	public Optional<Equipe> findById(Long id);

	public void deleteById(Long id);

}
