package de.betaradion.biosearcher.model.raw;

import org.json.simple.JSONArray;

public abstract class BSCollectionStructure {
	protected BSDataStructure[] collection;

	public BSCollectionStructure() {

	}

	@SuppressWarnings("unchecked")
	public JSONArray getJSONDescription(int levels) {
		JSONArray jArray = new JSONArray();
		for (BSDataStructure data : collection) {
			jArray.add(data.getJSONDescription(levels - 1));
		}
		return jArray;
	}

	public JSONArray getJSONDescription() {
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
