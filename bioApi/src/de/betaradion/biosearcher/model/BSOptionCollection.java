package de.betaradion.biosearcher.model;

import de.betaradion.biosearcher.model.raw.BSCollectionStructure;

public class BSOptionCollection extends BSCollectionStructure {

	public BSOptionCollection(int id) {
		super();

		collection = new BSOption[2];

		switch (id) {
		case 21:
			this.collection[0] = new BSOption(31);
			this.collection[1] = new BSOption(32);
			break;
		case 22:
			this.collection[0] = new BSOption(33);
			this.collection[1] = new BSOption(34);
			break;
		case 23:
			this.collection[0] = new BSOption(35);
			this.collection[1] = new BSOption(36);
			break;
		case 24:
			this.collection[0] = new BSOption(37);
			this.collection[1] = new BSOption(38);
			break;
		}
	}
}
