package bioSearcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet(description = "Directs to taskspecific classes that handle the data-management", urlPatterns = { "/Families/*" })
public class BSController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		JSONArray jArray = new JSONArray();
		JSONObject jObject = new JSONObject();


		if (!pathInfo.isEmpty()) {
			String[] pathSteps = pathInfo.split("/");
			if (pathSteps.length <= 1) {

				BSFamilies families = new BSFamilies();

				for (BSFamily family: families.getFamilies()) {
					jObject.clear();
					jObject.put(family.getId(), family.getName());
					jArray.add(jObject.clone());
				}
				System.out.print(jArray);
			} else {
				//specific familie_id is given -> handle further action
				int fId = Integer.parseInt(pathSteps[1]);
				if (pathSteps.length >= 3) {
					// After Fid something is given -> maybe character
					if (pathSteps[2].equals("characters")) {

						//Handle Character
						jArray = new JSONArray();
						jObject = new JSONObject();

						BSFamily family= new BSFamily(fId);

						for (BSCharacter character: family.getCharacters()) {
							jObject.clear();
							jObject.put(character.getId(), character.getName());
							jArray.add(jObject.clone());
						}
						System.out.print(jArray);
					}
				} else {
					// Handle Family
					jObject = new JSONObject();
					BSFamily family = new BSFamily(fId);

					jObject.put("id", family.getId());
					jObject.put("name", family.getName());
					jArray.add(jObject);
					System.out.print(jObject);	
				}
			}
		} else {
			System.out.print("No Parameters are given.");
		}		
	}
}
