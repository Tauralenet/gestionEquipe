package com.fr.adaming.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IEquipeDao;
import com.fr.adaming.entity.Equipe;

@Service
public class EquipeService implements IEquipeService {

	@Autowired
	private IEquipeDao dao;
	
	Logger log = Logger.getLogger(EquipeService.class);
	
// ******************************* Methode Create **********************************************
	public Equipe create(Equipe entity) {
		if (entity.getId() == null || entity.getId() == 0) {
			try {
				entity = dao.save(entity);
				log.info("Message de log (INFO) : l'utilisateur a été créé: " + entity.getId());

			} catch (Exception e) {
				if (e instanceof IllegalArgumentException) {
					log.info("Message de log (INFO) : l'ID est nul l'operation n'a pas été effectuée");
					return null;
				} else if (e instanceof DataIntegrityViolationException) {
					log.warn("Message de log (INFO) : le mail existe déjà " + e);
					return null;
				} else {
					log.error("erreur inconnue", e);
					return null;
				}
			}
		} else if (dao.existsById(entity.getId())) {
			log.info("Message de log (INFO) : l'id existe déjà l'insertion n'a pas été effectuée, id : "
					+ entity.getId());
			return null;
		} else {
			entity = dao.save(entity);
			log.info("Message de log (INFO) :DEBUG " + entity.getId());
			return entity;
		}
		return entity;
	}

// ******************************* Methode Update **********************************************

	public Equipe update(Equipe entity) {
		if (entity.getId() == null || entity.getId() == 0) {
			try {
				log.info("Message de log (INFO) : l'id utilisateur est null ");
				return null;
			} catch (Exception e) {
				if (e instanceof IllegalArgumentException) {
					log.info("Message de log (INFO) : l'ID est null l'operation n'a pas été effectuée");
					return null;
				} else {
					log.error("erreur inconnue", e);
					return null;
				}
			}
		} else if (dao.existsById(entity.getId())) {
			try {
				entity = dao.save(entity);
				log.info("Message de log (INFO) : l'insertion a été effectuée, id : " + entity.getId());

			} catch (Exception e) {
				if (e instanceof DataIntegrityViolationException) {
					log.warn("Message de log (INFO) : le mail existe déjà " + e);
					return null;
				}
			}
		}else {
			entity = dao.save(entity);
			log.info("Message de log (INFO) :DEBUG " + entity.getId());
			return entity;
		}
		return entity;
	}
// ******************************* Methode Read **********************************************


	public List<Equipe> findAll() {
		return dao.findAll();
	}

	public Equipe findById(Long id) {
		if (id == null || id == 0) {
			try {
				log.info("Message de log (INFO) : l'id demandé est null ");
				return null;
			} catch (Exception e) {
				if (e instanceof IllegalArgumentException) {
					log.info("Message de log (INFO) : l'ID est null l'operation n'a pas été effectuée");
					return null;
				}
			}
		} else if (dao.existsById(id)) {
			log.info("Message de log (INFO) : l'id demandé est le suivant " + id);
		}
		return dao.findById(id).get();
	}
// ******************************* Methode Delete **********************************************

	public boolean deleteById(Long id) {
			try {
				dao.deleteById(id);
				boolean result = !dao.existsById(id);
				return true;
			} catch (Exception e) {
				if (e instanceof IllegalArgumentException) {
					log.info("Message de log (INFO) : l'ID est null l'operation n'a pas été effectuée");
					return false;
				}
			return false; 
			}
		}


	
}
