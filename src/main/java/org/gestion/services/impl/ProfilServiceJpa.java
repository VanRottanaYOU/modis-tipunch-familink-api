package org.gestion.services.impl;

import org.gestion.entite.Profil;
import org.gestion.services.IProfilService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Service(value = "profilServiceJpa")
public class ProfilServiceJpa implements IProfilService {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void create(Profil nouveauProfil) {
		em.persist(nouveauProfil);
	}	
	
	@Override
	@Transactional
	public void update(Profil profil) {

		Query query = em.createQuery("FROM Profil c WHERE c.nom=:Nom");
		query.setParameter("Nom", profil.getNom());
		Profil oldProfil = (Profil) query.getSingleResult();
		if (!oldProfil.equals(null)) {
			oldProfil.setCouleur(profil.getCouleur());
			em.merge(oldProfil);
			em.flush();
		}
	}

	@Override
	public List<Profil> getProfils() {
		TypedQuery<Profil> query = em.createQuery("FROM Profil", Profil.class);
		return query.getResultList();
	}

	@Override
	public void deleteProfil(int id) {
		Profil profil = getProfilById(id);
		em.remove(profil);
	}

	@Override
	public Profil getProfilById(int id) {
		Profil profil = em.find(Profil.class, id);
	    return profil;
	}

}
