package com.fr.adaming.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	
	@Column(unique=true)
	private String email;
	private String pwd;
	
	@ManyToOne
	@JoinColumn(name="equipe")
	private Equipe nbEquipe;

	public User(Long id, String nom, String email, String pwd) {
		super();
		this.id = id;
		this.nom = nom;
		this.email = email;
		this.pwd = pwd;
	} 
	
}
