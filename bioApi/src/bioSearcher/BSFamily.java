package bioSearcher;

import org.json.simple.JSONObject;

public class BSFamily extends BSDataStructure {
	private BSCharacterCollection characters;

	public BSFamily(int id){
		super(id);
		
		characters = new BSCharacterCollection(id);

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

	public BSCharacterCollection getCharacters() {
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

	@Override
	public String getJSONDescription(int levels) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadSubmodules(int levels) {
		// TODO Auto-generated method stub
		
	}
}
