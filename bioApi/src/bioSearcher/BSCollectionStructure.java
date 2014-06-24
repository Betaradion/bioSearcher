package bioSearcher;

import org.json.simple.JSONArray;

public abstract class BSCollectionStructure {
	private BSDataStructure[] collection;
	
	public BSCollectionStructure(){
		
	}
	
	@SuppressWarnings("unchecked")
	public String getJSONDescription(int levels) {
		JSONArray jArray = new JSONArray();
		for (BSDataStructure data : collection) {
			jArray.add(data.getJSONDescription(levels -1));
		}
		return jArray.toJSONString();
	}
	
	public String getJSONDescription(){
		return getJSONDescription(0);
	}
	
	public void loadSubModules(int levels) {
		for (BSDataStructure data : collection) {
			data.loadSubmodules(levels - 1);
		}
	}
	
	public void loadSubModules() {
		loadSubModules(1000);
	}
	
}
