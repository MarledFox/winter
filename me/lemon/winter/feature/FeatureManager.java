package me.lemon.winter.feature;

import java.util.HashMap;

public class FeatureManager extends HashMap<String, Feature> {
	public void put(Feature feature) {
		super.put(feature.getName().toLowerCase(), feature);
	}

	@Override
	public Feature get(Object key) {
		return super.get(((String)key).toLowerCase());
	}
}
