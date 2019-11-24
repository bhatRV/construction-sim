package com.oracle.clearing.sim.commands;


import com.oracle.clearing.sim.entities.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Calculated the list of positions for an Advance command.
 * Assumption: START FROM NEXT SQUARE
 */
public class AdvanceCommand implements Command{

    private int step;

    public AdvanceCommand(int step) {
        this.step = step;
    }

    public List<Location> apply(Location currentLocation) {

        List<Location> path = new ArrayList<>();

        if (currentLocation != null) {

            int row = currentLocation.getX();
            int col = currentLocation.getY();

            switch (currentLocation.getDirection()){
                case WEST:
                    return IntStream.iterate(col - 1, i -> i - 1)
                            .limit(step)
                            .mapToObj(i -> new Location(row, i, currentLocation.getDirection()))
                            .collect(Collectors.toList());
                case EAST:
                    return IntStream.rangeClosed(col + 1, col + step)
                            .map(i -> i++)
                            .mapToObj(i -> new Location(row, i, currentLocation.getDirection()))
                            .collect(Collectors.toList());
                case NORTH:
                    return IntStream.iterate(row - 1, i -> i - 1)
                            .limit(step)
                            .mapToObj(i -> new Location(i, col, currentLocation.getDirection()))
                            .collect(Collectors.toList());
                case SOUTH:
                    return IntStream.rangeClosed(row + 1, row + step)
                            .map(i -> i++)
                            .mapToObj(i -> new Location(i, col, currentLocation.getDirection()))
                            .collect(Collectors.toList());
                default:
                    return path;
            }
        }

        return path;
    }

    public int getStep() {
        return step;
    }

    @Override
    public String toString() {
        return " FORWARD "+ step;
    }
}
