package bioSearcher;

import org.json.simple.JSONObject;

public class BSFamily extends BSDataStructure {
	BSCharacterCollection characters;
	public BSFamily(int id){
		super(id);

		switch(id){
		case 11:
			this.name = "Hunde";
			break;
		case 12: 
			name = "Fische";
			break;
		default:
			this.name = "Class undefined";
			break;
		}

	}


	@SuppressWarnings("unchecked")
	@Override
	public String getJSONDescription(int levels) {
		JSONObject jObject = new JSONObject();	

		jObject.put("id", BSFamily.this.getId());
		jObject.put("name", BSFamily.this.getName());
		
		if(levels>=1) {
			if(characters==null){loadSubmodules(0);}
			characters.getJSONDescription(levels-1);
		}

		return jObject.toJSONString();
	}


	@Override
	public void loadSubmodules(int levels) {
		if(characters== null){characters = new BSCharacterCollection(id);}
		if(levels >=0){characters.loadSubModules(levels-1);}
	}
}
