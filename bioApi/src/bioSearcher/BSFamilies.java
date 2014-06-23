package bioSearcher;

import org.json.simple.JSONObject;

public class BSFamilies {
	private BSFamily[] families;
	
	public BSFamilies(){
		families = new BSFamily[2];
		
		families[0] = new BSFamily(11);
		families[1] = new BSFamily(12);
	}
	
	public BSFamily[] getFamilies() {
		return families;
	}

	@SuppressWarnings("unchecked")
	public String getJSONDescription() {
		//getJSONDesciption
		JSONObject jObject = new JSONObject();	
		
		for (BSFamily family: families) {
			jObject.put("ID:", family.getId());
			jObject.put("Name:", family.getName());
		}
		
		return jObject.toJSONString();
	}
}