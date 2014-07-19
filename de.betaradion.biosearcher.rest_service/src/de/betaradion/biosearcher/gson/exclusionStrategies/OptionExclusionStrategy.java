package de.betaradion.biosearcher.gson.exclusionStrategies;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class OptionExclusionStrategy implements ExclusionStrategy {

	@Override
	public boolean shouldSkipClass(Class<?> arg0) {
		return false;
	}

	@Override
	public boolean shouldSkipField(FieldAttributes att) {
		if (att.getName().equals("Character")
				|| att.getName().equals("MatchTable")) {
			return true;
		}
		return false;
	}

}
