package bioSearcher;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class BSFamily extends BSDataStorage {
	private BSCharacter[] characters = new BSCharacter[2];

	public BSFamily(int id){
		super(id);

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

	public BSCharacter[] getCharacters() {
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
