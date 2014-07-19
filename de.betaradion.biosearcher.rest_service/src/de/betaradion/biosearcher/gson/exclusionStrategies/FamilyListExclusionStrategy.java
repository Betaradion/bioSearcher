package de.betaradion.biosearcher.gson.exclusionStrategies;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import de.betaradion.biosearcher.model.Family;

public class FamilyListExclusionStrategy implements ExclusionStrategy {

	@Override
	public boolean shouldSkipClass(Class<?> arg0) {
		return false;
	}

	@Override
	public boolean shouldSkipField(FieldAttributes att) {
		try {
			if (att.equals(Family.class.getField("characters"))) {
				return true;
			}
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		return false;
	}

}
