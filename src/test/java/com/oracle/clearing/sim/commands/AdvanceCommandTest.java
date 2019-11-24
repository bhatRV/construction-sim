package com.oracle.clearing.sim.commands;

import com.oracle.clearing.sim.entities.Location;
import com.oracle.clearing.sim.entities.enums.Direction;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdvanceCommandTest {

    @Test
    public void advanceToEastWorks() {

        AdvanceCommand advanceCommand = new AdvanceCommand(4);

        Location currentLocation = new Location(0,0, Direction.EAST);
        List<Location> expected = new ArrayList<>();
        expected.add(new Location(0, 1, Direction.EAST));
        expected.add(new Location(0, 2, Direction.EAST));
        expected.add(new Location(0, 3, Direction.EAST));
        expected.add(new Location(0, 4, Direction.EAST));

        assertEquals(4, advanceCommand.getStep());

        List<Location> actual = advanceCommand.apply(currentLocation);

        assertEquals(4, actual.size());
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void advanceToWestWorks() {

        AdvanceCommand advanceCommand = new AdvanceCommand(5);

        Location currentLocation = new Location(1,5, Direction.WEST);
        List<Location> expected = new ArrayList<>();
        expected.add(new Location(1, 4, Direction.WEST));
        expected.add(new Location(1, 3, Direction.WEST));
        expected.add(new Location(1, 2, Direction.WEST));
        expected.add(new Location(1, 1, Direction.WEST));
        expected.add(new Location(1, 0, Direction.WEST));

        assertEquals(5, advanceCommand.getStep());

        List<Location> actual = advanceCommand.apply(currentLocation);

        System.out.println("test");

        assertEquals(5, actual.size());
        assertArrayEquals(expected.toArray(), actual.toArray());


    }

    @Test
    public void advanceToSouthWorks() {

        AdvanceCommand advanceCommand = new AdvanceCommand(4);

        Location currentLocation = new Location(1,0, Direction.SOUTH);
        List<Location> expected = new ArrayList<>();
        expected.add(new Location(2, 0, Direction.SOUTH));
        expected.add(new Location(3, 0, Direction.SOUTH));
        expected.add(new Location(4, 0, Direction.SOUTH));
        expected.add(new Location(5, 0, Direction.SOUTH));

        assertEquals(4, advanceCommand.getStep());

        List<Location> actual = advanceCommand.apply(currentLocation);

        assertEquals(4, actual.size());
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void advanceToNorthWorks() {

        AdvanceCommand advanceCommand = new AdvanceCommand(4);

        Location currentLocation = new Location(4,5, Direction.NORTH);
        List<Location> expected = new ArrayList<>();
        expected.add(new Location(3, 5, Direction.NORTH));
        expected.add(new Location(2, 5, Direction.NORTH));
        expected.add(new Location(1, 5, Direction.NORTH));
        expected.add(new Location(0, 5, Direction.NORTH));

        assertEquals(4, advanceCommand.getStep());

        List<Location> actual = advanceCommand.apply(currentLocation);

        assertEquals(4, actual.size());
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

}