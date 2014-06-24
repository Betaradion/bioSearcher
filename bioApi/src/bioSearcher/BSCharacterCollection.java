package bioSearcher;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class BSCharacterCollection extends BSCollectionStructure{
	private BSCharacter[] characters;
	int id;

	public BSCharacterCollection(int id) {
		super();
		
		characters = new BSCharacter[2];

		switch(id){
		case 11:
			this.characters[0] = new BSCharacter(21);
			this.characters[1] = new BSCharacter(22);
			break;
		case 12: 
			this.characters[0] = new BSCharacter(23);
			this.characters[1] = new BSCharacter(24);
			break;
		default:
			break;
		}
	}

	@SuppressWarnings("unchecked")
	public String getJSONDescription() {
		//TODO
		JSONObject json = new JSONObject();
		JSONArray JSONoptions = new JSONArray();

		json.put("id", this.id);
		json.put("option1", this.characters[0]);
		json.put("option1", this.characters[1]);

		JSONoptions.add(json.clone());

		json.clear();

		return JSONoptions.toJSONString();
	}

	@Override
	public String getJSONDescription(int n) {
		return getJSONDescription() + getJSONDescription(n);
	}

	@Override
	public void loadSubModules(int levels) {
		// TODO Auto-generated method stub
		
	}

}
