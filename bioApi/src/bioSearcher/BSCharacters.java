package bioSearcher;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class BSCharacters extends BSDataStorage{
	private BSCharacter[] characters;
	int id;

	public BSCharacters(int id) {
		super(id);

		characters = new BSCharacter[2];

		switch(id){
		case 11:
			this.name = "Hunde";
			this.characters[0] = new BSCharacter(21);
			this.characters[1] = new BSCharacter(22);
			break;
		case 12: 
			name = "Fische";
			this.characters[0] = new BSCharacter(23);
			this.characters[1] = new BSCharacter(24);
			break;
		default:
			this.name = "Class undefined";
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

}
