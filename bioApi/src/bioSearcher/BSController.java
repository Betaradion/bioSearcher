package bioSearcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(description = "Directs to taskspecific classes that handle the data-management", urlPatterns = { "/Families/*" })
public class BSController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();

		if (!pathInfo.isEmpty()) {
			String[] pathSteps = pathInfo.split("/");
			if (pathSteps.length <= 1) {

				BSFamilyCollection families = new BSFamilyCollection();
				response.getWriter().print(families.getJSONDescription());
			} else {
				//specific familie_id is given -> handle further action
				int fId = Integer.parseInt(pathSteps[1]);
				if (pathSteps.length >= 3) {
					// After Fid something is given -> maybe character
					if (pathSteps[2].equals("characters")) {

						//Handle Character
						BSFamily family = new BSFamily(fId);

						response.getWriter().print(family.getJSONDescription());
					}
				} else {
					BSFamily family = new BSFamily(fId);
					response.getWriter().print(family.getJSONDescription());	
				}
			}
		} else {
			response.getWriter().print("No Parameters are given.");
		}		
	}
}
