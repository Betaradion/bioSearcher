package bioSearcher;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class BSFamilyCollection extends BSCollectionStructure{
	private BSFamily[] families;
	
	public BSFamilyCollection(){
		families = new BSFamily[2];
		
		families[0] = new BSFamily(11);
		families[1] = new BSFamily(12);
	}
	
	public BSFamily[] getFamilies() {
		return families;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getJSONDescription(int n) {
		//getJSONDesciption
		JSONObject jObject = new JSONObject();
		JSONArray jArray = new JSONArray();
		
		for (BSFamily family: families) {
			jObject.put("id", family.getId());
			jObject.put("name", family.getName());
			jArray.add(jObject.clone());
			jObject.clear();
		}
		
		return jArray.toJSONString();
	}

	@Override
	public void loadSubModules(int n) {
		// TODO Auto-generated method stub
		
	}
	
	

}