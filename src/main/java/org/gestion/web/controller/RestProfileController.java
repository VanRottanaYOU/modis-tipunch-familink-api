package org.gestion.web.controller;

import java.util.List;

import org.gestion.entite.Profil;
import org.gestion.services.IProfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profil")
public class RestProfileController {
	
	@Autowired
	@Qualifier("profilServiceJpa")
	private IProfilService profilServiceJpa;
	
	@Autowired
	@Qualifier("profilServiceRepository")
	private IProfilService profilServiceRepository;
	
	// ********************************** //
	// ******** GET LIST profils ******** //
	// ********************************** //

	@RequestMapping(path = "/profils", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Profil> getUtilisateursWithJPA() {
		return profilServiceJpa.getProfils();
	}
	
	// *********************************** //
	// ********** CREATE profil ********** //
	// *********************************** //

	@RequestMapping(path = "/createProfil", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
	public void CreateProfil(@RequestBody Profil newProfil) {
		
		System.out.println("coucou");
		profilServiceJpa.create(new Profil(newProfil.getNom(), newProfil.getCouleur()));

	}
	
	// *********************************** //
	// ******* GET utilisateur BY ID ********** //
	// *********************************** //

	@RequestMapping(path = "/{idProfil}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Profil getProfilById(@PathVariable("idProfil") String idProfil) {
		return profilServiceRepository.getProfilById(Integer.parseInt(idProfil));
	}
	
	// *********************************** //
	// ******* DELETE utilisateur BY ID ******** //
	// *********************************** //

	@RequestMapping(path = "/delete/{idProfil}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteProfil(@PathVariable("idProfil") String idProfil) {
		profilServiceRepository.deleteProfil(Integer.parseInt(idProfil));

	}

}