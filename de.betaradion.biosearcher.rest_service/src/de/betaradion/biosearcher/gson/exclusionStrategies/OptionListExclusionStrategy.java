package de.betaradion.biosearcher.gson.exclusionStrategies;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.sun.research.ws.wadl.Option;

public class OptionListExclusionStrategy implements ExclusionStrategy {

	@Override
	public boolean shouldSkipClass(Class<?> arg0) {
		return false;
	}

	@Override
	public boolean shouldSkipField(FieldAttributes att) {
		try {
			if (att.equals(Option.class.getField("Character"))
					|| att.equals(Option.class.getField("MatchTable"))) {
				return true;
			}
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
