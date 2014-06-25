package de.betaradion.biosearcher.rest_service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.betaradion.biosearcher.model.BSCharacter;
import de.betaradion.biosearcher.model.BSCharacterCollection;
import de.betaradion.biosearcher.model.BSFamily;
import de.betaradion.biosearcher.model.BSFamilyCollection;
import de.betaradion.biosearcher.model.BSOption;
import de.betaradion.biosearcher.model.BSOptionCollection;

@Path("/families")
public class BSController {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		BSFamilyCollection familyCollection = new BSFamilyCollection();
		return familyCollection.getJSONDescription().toJSONString();
	}

	@Path("/{fid}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String showFamilyWithID(@PathParam("fid") int fid) {
		BSFamily family = new BSFamily(fid);
		return family.getJSONDescription().toJSONString();
	}

	@Path("/{fid}/characters")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String showCharactersofFamily(@PathParam("fid") int fid) {
		BSCharacterCollection characterCollection = new BSCharacterCollection(
				fid);
		return characterCollection.getJSONDescription().toJSONString();
	}

	@Path("/{fid}/characters/{cid}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String showCharacter(@PathParam("cid") int cid) {
		BSCharacter character = new BSCharacter(cid);
		return character.getJSONDescription().toJSONString();
	}

	@Path("/{fid}/characters/{cid}/options")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String showOptions(@PathParam("cid") int cid) {
		BSOptionCollection character = new BSOptionCollection(cid);
		return character.getJSONDescription().toJSONString();
	}

	@Path("/{fid}/characters/{cid}/options/{oid}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String showOptionForId(@PathParam("oid") int oid) {
		BSOption option = new BSOption(oid);
		return option.getJSONDescription().toJSONString();
	}
}