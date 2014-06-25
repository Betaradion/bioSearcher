package bioSearcher;

import org.json.simple.JSONObject;

public class BSOption extends BSDataStructure {
	public BSOption(int id){
		super(id);
		
		switch(id){
		case 31:
			this.name = "big";
			break;
		case 32:
			this.name = "small";
			break;
		case 33:
			this.name = "bright";
			break; 
		case 34:
			this.name = "dark";
			break;
		case 35:
			this.name = "round";
			break;
		case 36:
			this.name = "linear";
			break;
		case 37:
			this.name = "forest";
			break;
		case 38:
			this.name = "meadow";
			break;
		default:
			this.name = "Character undefined.";
			break;

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getJSONDescription(int levels) {
		JSONObject json = new JSONObject();
		json.put("id", this.id);
		json.put("name", this.id);
		
		return json.toJSONString();
	}

	@Override
	public void loadSubmodules(int levels) {}
}
