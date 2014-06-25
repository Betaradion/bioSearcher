package de.betaradion.biosearcher.model;

import de.betaradion.biosearcher.model.raw.BSCollectionStructure;



public class BSCharacterCollection  extends BSCollectionStructure{

	public BSCharacterCollection(int id) {
		super();

		collection = new BSCharacter[2];
		
		switch(id){
		case 11:
			this.collection[0] = new BSCharacter(21);
			this.collection[1] = new BSCharacter(22);
			break;
		case 12: 
			this.collection[0] = new BSCharacter(23);
			this.collection[1] = new BSCharacter(24);
			break;
		default:
			break;
		}
	}

}