package bioSearcher;

import org.json.simple.JSONArray;

public abstract class BSCharacterCollection  extends BSCollectionStructure{
	private BSCharacter[] characters;

	public BSCharacterCollection(){
		super();
	}

	@SuppressWarnings("unchecked")
	public String getJSONDescription(int levels) {
		JSONArray jArray = new JSONArray();
		for (BSDataStructure data : characters) {
			jArray.add(data.getJSONDescription(levels -1));
		}
		return jArray.toJSONString();
	}

	public String getJSONDescription(){
		return getJSONDescription(0);
	}

	public void loadSubModules(int levels) {
		for (BSDataStructure data : characters) {
			data.loadSubmodules(levels - 1);
		}
	}

	public void loadSubModules() {
		loadSubModules(1000);
	}

}