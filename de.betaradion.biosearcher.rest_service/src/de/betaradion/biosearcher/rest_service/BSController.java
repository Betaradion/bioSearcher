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

import org.codehaus.jackson.map.annotate.JsonView;

import de.betaradion.biosearcher.model.Character;
import de.betaradion.biosearcher.model.Family;
import de.betaradion.biosearcher.model.Option;
import de.betaradion.biosearcher.model.jackson.Views;

@Path("/families")
public class BSController {

	EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("bioSearcher");
	EntityManager em = factory.createEntityManager();

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

	@Path("/{fid}")
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

	@Path("/{fid}/characters")
	@JsonView(Views.CharacterListView.class)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Character[] showCharactersofFamily(@PathParam("fid") int fid) {
		em.getTransaction().begin();
		TypedQuery<Character> charactersQuery = em.createNamedQuery(
				"Character.findByFID", Character.class);
		charactersQuery.setParameter("id", fid);
		List<Character> characters = charactersQuery.getResultList();
		em.close();
		return characters.toArray(new Character[characters.size()]);
	}

	@Path("/{fid}/characters/{cid}")
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

	@Path("/{fid}/characters/{cid}/options")
	@JsonView(Views.OptionListView.class)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Option[] showOptions(@PathParam("cid") int cid) {
		em.getTransaction().begin();
		TypedQuery<Option> optionsQuery = em.createNamedQuery(
				"Option.findByCID", Option.class);
		optionsQuery.setParameter("id", cid);
		List<Option> options = optionsQuery.getResultList();
		em.close();

		return options.toArray(new Option[options.size()]);
	}

	@Path("/{fid}/characters/{cid}/options/{oid}")
	@JsonView(Views.OptionView.class)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Option showOptionForId(@PathParam("oid") int oid) {
		em.getTransaction().begin();
		TypedQuery<Option> optionQuery = em.createNamedQuery(
				"Option.findByOID", Option.class);
		optionQuery.setParameter("id", oid);
		Option option = optionQuery.getSingleResult();
		em.close();

		return option;
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