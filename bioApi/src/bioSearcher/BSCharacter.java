package bioSearcher;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class BSCharacter extends BSDataStructure {
	protected BSOptionCollection options;

	public BSCharacter(int id){
		super(id);
		
		options = new BSOptionCollection(id);
		
		switch(id){
		case 21:
			this.name = "Size";
			this.options[0] = new BSOption(31);
			this.options[1] = new BSOption(32);
			break;
		case 22:
			this.name = "Color";
			this.options[0] = new BSOption(33);
			this.options[1] = new BSOption(34);
			break;
		case 23:
			this.name = "Form";
			this.options[0] = new BSOption(35);
			this.options[1] = new BSOption(36);
			break;
		case 24:
			this.name = "Habitat";
			this.options[0] = new BSOption(37);
			this.options[1] = new BSOption(38);
			break;
		default:
			this.name = "Character undefined.";
			break;

		}
	}


	@SuppressWarnings("unchecked")
	public String getJSONDescription() {
		JSONObject json = new JSONObject();
		json.put("id", this.id);
		json.put("name", this.id);

		JSONArray JSONoptions = new JSONArray();

		for (BSOption option : options) {
			JSONObject JSONoption = new JSONObject();
			JSONoption.put("id", option.id);
			JSONoption.put("name", option.name);

			JSONoptions.add(JSONoption.clone());

		}

		json.put("options",	JSONoptions);

		return json.toJSONString();
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
