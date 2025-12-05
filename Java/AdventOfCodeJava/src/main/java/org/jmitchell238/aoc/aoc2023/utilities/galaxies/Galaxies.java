package org.jmitchell238.aoc.aoc2023.utilities.galaxies;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Galaxies {
    private final List<Galaxy> galaxyList = new ArrayList<>();

    public void addGalaxy(Galaxy galaxy) {
        galaxyList.add(galaxy);
    }

    public void removeGalaxy(Galaxy galaxy) {
        galaxyList.remove(galaxy);
    }

    public Galaxy getGalaxyById(int id) {
        return galaxyList.get(id - 1);
    }
}
