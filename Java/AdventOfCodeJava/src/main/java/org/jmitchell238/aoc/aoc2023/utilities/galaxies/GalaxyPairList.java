package org.jmitchell238.aoc.aoc2023.utilities.galaxies;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class GalaxyPairList {
    private final List<GalaxyPair> galaxyPairs = new ArrayList<>();

    public void addGalaxyPair(GalaxyPair galaxyPair) {
        galaxyPairs.add(galaxyPair);
    }

    public void removeGalaxyPair(GalaxyPair galaxyPair) {
        galaxyPairs.remove(galaxyPair);
    }

    public long getSumOfShortestDistances() {
        long sum = 0;
        for (GalaxyPair galaxyPair : galaxyPairs) {
            sum += galaxyPair.distance();
        }
        return sum;
    }
}
