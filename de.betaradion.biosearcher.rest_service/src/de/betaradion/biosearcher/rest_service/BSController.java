package de.betaradion.biosearcher.rest_service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import de.betaradion.biosearcher.model.Family;

@Path("/families")
public class BSController {

	EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("bioSearcher");

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Family> familiesQuery = em.createNamedQuery(
				"Family.findAll", Family.class);
		List<Family> families = familiesQuery.getResultList();
		em.close();
		Gson gson = new Gson();
		String json = gson.toJson(families);
		return json;
	}

	@Path("/create")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String createFunction() {

		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Family fam = new Family();
		em.getTransaction().commit();
		em.close();

		return null;
	}

	@Path("/{fid}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String showFamilyWithID(@PathParam("fid") int fid) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Family> familiesQuery = em.createNamedQuery(
				"Family.findByID", Family.class);
		familiesQuery.setParameter("id", fid);
		List<Family> families = familiesQuery.getResultList();
		em.close();
		Gson gson = new Gson();
		String json = gson.toJson(families);
		return json;
	}

	@Path("/{fid}/characters")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String showCharactersofFamily(@PathParam("fid") int fid) {

		return null;
	}

	@Path("/{fid}/characters/{cid}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String showCharacter(@PathParam("cid") int cid) {

		return null;
	}

	@Path("/{fid}/characters/{cid}/options")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String showOptions(@PathParam("cid") int cid) {

		return null;
	}

	@Path("/{fid}/characters/{cid}/options/{oid}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String showOptionForId(@PathParam("oid") int oid) {

		return null;
	}

}