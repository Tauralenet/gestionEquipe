package com.fr.adaming;


import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.fr.adaming.entity.Equipe;
import com.fr.adaming.entity.User;
import com.fr.adaming.service.IEquipeService;
import com.fr.adaming.service.IUserService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class TeamSpringApplication {

	public static void main(String[] args) {
		ApplicationContext ctxt =  SpringApplication.run(TeamSpringApplication.class, args);
		IUserService serv1 = ctxt.getBean(IUserService.class);
		serv1.create(new User(null, "nom1", "mail1", "a", null));
		IEquipeService serv2 = ctxt.getBean(IEquipeService.class);
		serv2.create(new Equipe(null, "nom2", "lol"));
		
		Logger log = Logger.getLogger(TeamSpringApplication.class);
			log.info("Message de log (INFO)");
			log.warn("Message de log (WARN)");
			log.error("Message de log (ERROR)");
			log.fatal("Message de log (FATAL)");

	}

}
