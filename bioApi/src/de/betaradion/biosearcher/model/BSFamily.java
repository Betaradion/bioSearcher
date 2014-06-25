package de.betaradion.biosearcher.model;

import org.json.simple.JSONObject;

import de.betaradion.biosearcher.model.raw.BSDataStructure;

public class BSFamily extends BSDataStructure {
	BSCharacterCollection characters;

	public BSFamily(int id) {
		super(id);

		switch (id) {
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
	public JSONObject getJSONDescription(int levels) {
		JSONObject jObject = new JSONObject();

		jObject.put("id", BSFamily.this.getId());
		jObject.put("name", BSFamily.this.getName());

		if (levels >= 1) {
			if (characters == null) {
				loadSubmodules(0);
			}
			characters.getJSONDescription(levels - 1);
		}

		return jObject;
	}

	@Override
	public void loadSubmodules(int levels) {
		if (characters == null) {
			characters = new BSCharacterCollection(id);
		}
		if (levels >= 0) {
			characters.loadSubModules(levels - 1);
		}
	}
}
