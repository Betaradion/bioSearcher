package de.betaradion.biosearcher.model;

import de.betaradion.biosearcher.model.raw.BSCollectionStructure;

public class BSSpeciesCollection extends BSCollectionStructure{

	public BSSpeciesCollection(){
		collection = new BSFamily[4];

		collection[0] = new BSSpecies(11);
		collection[1] = new BSSpecies(12);
		collection[2] = new BSSpecies(13);
		collection[3] = new BSSpecies(14);
		
		}
	
	
}
