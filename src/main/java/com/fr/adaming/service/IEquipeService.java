package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Equipe;


public interface IEquipeService {

	public Equipe create(Equipe entity);
	
	public Equipe update(Equipe entity);

	public List<Equipe> findAll();

	public Equipe findById(Long id);

	public boolean deleteById(Long id);

}
