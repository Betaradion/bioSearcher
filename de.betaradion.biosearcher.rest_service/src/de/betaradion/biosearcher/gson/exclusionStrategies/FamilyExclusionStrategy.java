package de.betaradion.biosearcher.gson.exclusionStrategies;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class FamilyExclusionStrategy implements ExclusionStrategy {

	@Override
	public boolean shouldSkipClass(Class<?> arg0) {
		return false;
	}

	@Override
	public boolean shouldSkipField(FieldAttributes att) {
		if (att.getName().equals("family") || att.getName().equals("character")
				|| att.getName().equals("matchTables")) {
			return true;
		}
		return false;
	}
}
