package de.betaradion.biosearcher.rest_service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

@Path("/search")
public class BSSearchController {
	// BSSpeciesCollection speciesCollection;

	public BSSearchController() {
		// speciesCollection = new BSSpeciesCollection();
	}

	@GET
	public String searchForSpeciesByParameters(@Context UriInfo ui) {
		MultivaluedMap<String, String> queryParams = ui.getQueryParameters();

		return "";
	}
}
