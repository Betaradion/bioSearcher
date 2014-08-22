package de.betaradion.biosearcher.rest_service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.annotation.JsonView;

import de.betaradion.biosearcher.model.Character;
import de.betaradion.biosearcher.model.Family;
import de.betaradion.biosearcher.model.jackson.Views;

@Path("/api")
public class BSController {

	EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("bioSearcher");
	EntityManager em = factory.createEntityManager();

	@Path("/families")
	@JsonView(Views.FamilyListView.class)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Family[] showFamilies() {
		em.getTransaction().begin();
		TypedQuery<Family> familiesQuery = em.createNamedQuery(
				"Family.findAll", Family.class);
		List<Family> families = familiesQuery.getResultList();
		em.close();

		return families.toArray(new Family[families.size()]);
	}

	@Path("/family/{fid}")
	@JsonView(Views.FamilyView.class)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Family showFamilyWithID(@PathParam("fid") int fid) {
		em.getTransaction().begin();
		TypedQuery<Family> familiesQuery = em.createNamedQuery(
				"Family.findByID", Family.class);
		familiesQuery.setParameter("id", fid);
		Family family = familiesQuery.getSingleResult();
		em.close();

		return family;
	}

	@Path("/character/{cid}")
	@JsonView(Views.CharacterView.class)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Character showCharacter(@PathParam("cid") int cid) {
		em.getTransaction().begin();
		TypedQuery<Character> characterQuery = em.createNamedQuery(
				"Character.findByCID", Character.class);
		characterQuery.setParameter("id", cid);
		Character character = characterQuery.getSingleResult();
		em.close();

		return character;
	}

	@Path("/full")
	@JsonView(Views.FullView.class)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Family[] getFullClone() {
		em.getTransaction().begin();
		TypedQuery<Family> familiesQuery = em.createNamedQuery(
				"Family.findAll", Family.class);
		List<Family> families = familiesQuery.getResultList();
		em.close();

		return families.toArray(new Family[families.size()]);
	}

	@Path("family")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String putFamily(String Family) {
		return "ok";
	}

	/*
	 * @Path("/create")
	 * 
	 * @GET
	 * 
	 * @Produces(MediaType.TEXT_PLAIN) public String createFunction() {
	 * 
	 * EntityManager em = factory.createEntityManager();
	 * em.getTransaction().begin(); Family fam = new Family();
	 * em.getTransaction().commit(); em.close();
	 * 
	 * return null; }
	 */

}