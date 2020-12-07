package me.lemon.winter.feature;

import java.util.HashMap;

public class FeatureManager extends HashMap<String, Feature> {
    public FeatureManager() {
    }

    public void put(Feature feature) {
        super.put(feature.getName().toLowerCase(), feature);
    }

    public Feature get(Object key) {
        return (Feature)super.get(((String)key).toLowerCase());
    }
}