package de.betaradion.biosearcher.model;

import org.json.simple.JSONObject;

import de.betaradion.biosearcher.model.raw.BSDataStructure;

public class BSSpecies extends BSDataStructure{
	protected BSCharacter[] characters;
	protected String name = "";
	
	public BSSpecies(int id){
		super(id);
		
		characters = new BSCharacter[2];
		
		switch(id){
		case 11:
			this.name = "Schaeferhund";
			this.characters[0] = new BSCharacter(21);
			this.characters[1] = new BSCharacter(22);
			break;
		case 12: 
			this.name = "Dogge";
			this.characters[0] = new BSCharacter(23);
			this.characters[1] = new BSCharacter(24);
			break;
		case 13:
			this.name = "Dackel";
			this.characters[0] = new BSCharacter(21);
			this.characters[1] = new BSCharacter(24);
		case 14:
			this.name = "Chihuahua";
			this.characters[0] = new BSCharacter(22);
			this.characters[1] = new BSCharacter(24);
		default:
			break;
		}
		
	}

	@Override
	public JSONObject getJSONDescription(int levels) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadSubmodules(int levels) {
		// TODO Auto-generated method stub
		
	}
}
