package de.betaradion.biosearcher.rest_service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.eclipse.persistence.jpa.JpaHelper;
import org.eclipse.persistence.queries.QueryByExamplePolicy;
import org.eclipse.persistence.queries.ReadObjectQuery;

import de.betaradion.biosearcher.model.Character;
import de.betaradion.biosearcher.model.Family;
import de.betaradion.biosearcher.model.MatchTable;
import de.betaradion.biosearcher.model.Option;
import de.betaradion.biosearcher.model.Species;

@Path("/search")
public class BSSearchController {
	EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("bioSearcher");
	EntityManager em = factory.createEntityManager();

	@GET
	public String searchForSpeciesByParameters(@Context UriInfo ui) {
		MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
		int fid = Integer.parseInt(queryParams.get("fid").get(0));
		queryParams.remove("fid");

		Species species = new Species();
		species.setFamily(new Family(fid));

		List<MatchTable> matches = new ArrayList<MatchTable>();

		for (Entry<String, List<String>> param : queryParams.entrySet()) {
			MatchTable match = new MatchTable();
			match.setCharacter(new Character(Integer.parseInt(param.getKey())));
			match.setOption(new Option(Integer
					.parseInt(param.getValue().get(0))));
			matches.add(match);
		}

		species.setMatches(matches);

		QueryByExamplePolicy policy = new QueryByExamplePolicy();
		policy.excludeDefaultPrimitiveValues();
		ReadObjectQuery q = new ReadObjectQuery(species, policy);

		// Wrap the native query in a standard JPA Query and execute it
		Query query = JpaHelper.createQuery(q, em);
		query.getResultList();

		return "";
	}
}
