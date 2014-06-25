package de.betaradion.biosearcher.model;

import org.json.simple.JSONObject;

import de.betaradion.biosearcher.model.raw.BSDataStructure;

public class BSCharacter extends BSDataStructure {
	protected BSOptionCollection options;

	public BSCharacter(int id) {
		super(id);

		switch (id) {
		case 21:
			this.name = "Size";
			break;
		case 22:
			this.name = "Color";
			break;
		case 23:
			this.name = "Form";
			break;
		case 24:
			this.name = "Habitat";
			break;
		default:
			this.name = "Character undefined.";
			break;

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getJSONDescription(int levels) {
		JSONObject json = new JSONObject();
		json.put("id", this.id);
		json.put("name", this.name);
		if (levels >= 1) {
			if (options == null) {
				loadSubmodules(0);
			}
			json.put("options", options.getJSONDescription(levels - 1));
		}

		return json;
	}

	@Override
	public void loadSubmodules(int levels) {
		if (options == null) {
			options = new BSOptionCollection(id);
		}
		if (levels >= 1) {
			options.loadSubModules(levels - 1);
		}
	}
}
