package de.betaradion.biosearcher.model;

import de.betaradion.biosearcher.model.raw.BSCollectionStructure;

public class BSFamilyCollection extends BSCollectionStructure {

	public BSFamilyCollection() {
		collection = new BSFamily[2];

		collection[0] = new BSFamily(11);
		collection[1] = new BSFamily(12);
	}
}