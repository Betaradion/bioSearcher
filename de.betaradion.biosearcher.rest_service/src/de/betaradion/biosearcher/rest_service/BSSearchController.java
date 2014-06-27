package de.betaradion.biosearcher.rest_service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;

import de.betaradion.biosearcher.model.BSFamily;

@Path("/search")
public class BSSearchController {

	@Path("/{fid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String searchSpeciesByParameters(@QueryParam("jsonArray")String jsonArray) {
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.
		
		JSONObject object = new JSONObject();
		object.
		
		return 
	}
}
