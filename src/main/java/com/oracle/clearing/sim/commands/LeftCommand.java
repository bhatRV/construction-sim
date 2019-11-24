package com.oracle.clearing.sim.commands;


import com.oracle.clearing.sim.entities.Location;

import java.util.ArrayList;
import java.util.List;

public class LeftCommand implements Command{

    @Override
    public List<Location> apply(Location currentLocation) {

        List<Location> path = new ArrayList<>();

        if (currentLocation != null) {

            currentLocation.setDirection(currentLocation.getDirection().turnLeft());
            path.add(currentLocation);

        }

        return path;
    }

    @Override
    public String toString() {
        return "<-- Turn LEFT";
    }
}
