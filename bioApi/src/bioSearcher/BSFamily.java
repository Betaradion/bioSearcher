package bioSearcher;

import org.json.simple.JSONObject;

public class BSFamily extends BSDataStorage {
	private BSCharacters characters;

	public BSFamily(int id){
		super(id);
		characters = new BSCharacters(id);
	}

	public BSCharacters getCharacters() {
		return this.characters;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getJSONDescription() {
		//getJSONDesciption
		JSONObject jObject = new JSONObject();	

		jObject.put("id", BSFamily.this.getId());
		jObject.put("name", BSFamily.this.getName());

		return jObject.toJSONString();
	}
}
