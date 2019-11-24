package com.oracle.clearing.sim.commands;

import com.oracle.clearing.sim.entities.Location;
import com.oracle.clearing.sim.entities.enums.Direction;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeftCommandTest {

    @Test
    public void turnLeftToEastWorks() {

        LeftCommand leftCommand = new LeftCommand();

        Location currentLocation = new Location(0,0, Direction.EAST);
        List<Location> expected = new ArrayList<>();
        expected.add(new Location(0, 0, Direction.NORTH));

        List<Location> actual = leftCommand.apply(currentLocation);

        assertEquals(1, actual.size());
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void turnLeftToWestWorks() {

        LeftCommand leftCommand = new LeftCommand();

        Location currentLocation = new Location(0,0, Direction.WEST);
        List<Location> expected = new ArrayList<>();
        expected.add(new Location(0, 0, Direction.SOUTH));

        List<Location> actual = leftCommand.apply(currentLocation);

        assertEquals(1, actual.size());
        assertArrayEquals(expected.toArray(), actual.toArray());

    }

    @Test
    public void turnLeftToSouthWorks() {

        LeftCommand leftCommand = new LeftCommand();

        Location currentLocation = new Location(0,0, Direction.SOUTH);
        List<Location> expected = new ArrayList<>();
        expected.add(new Location(0, 0, Direction.EAST));

        List<Location> actual = leftCommand.apply(currentLocation);

        assertEquals(1, actual.size());
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void turnLeftToNorthWorks() {

        LeftCommand leftCommand = new LeftCommand();

        Location currentLocation = new Location(0,0, Direction.NORTH);
        List<Location> expected = new ArrayList<>();
        expected.add(new Location(0, 0, Direction.WEST));

        List<Location> actual = leftCommand.apply(currentLocation);

        assertEquals(1, actual.size());
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

}