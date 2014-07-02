package de.betaradion.biosearcher.rest_service;

import java.util.Map.Entry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Path("/search")
public class BSSearchController {
	// BSSpeciesCollection speciesCollection;

	public BSSearchController() {
		// speciesCollection = new BSSpeciesCollection();
	}

	public String searchForSpeciesByParameters(@Context UriInfo ui) {
		MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();

		for (Entry<String, java.util.List<String>> entry : queryParams
				.entrySet()) {

			// do all the option
			switch (Integer.parseInt(entry.getKey())) {
			case 21:

				break;
			case 22:

				break;
			case 23:

				break;
			case 24:

				break;
			default:

				break;

			}

		}

		return jsonArray.toJSONString();
	}

	@SuppressWarnings("unchecked")
	@GET
	public String getTestSearch() {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", "Schaeferhund");
		jsonArray.add(jsonObject.clone());
		jsonObject.clear();
		jsonObject.put("name", "Labrador");
		jsonArray.add(jsonObject.clone());
		jsonObject.clear();
		jsonObject.put("name", "Chihuahua");
		jsonArray.add(jsonObject.clone());
		jsonObject.clear();

		return jsonArray.toJSONString();
	}

}
