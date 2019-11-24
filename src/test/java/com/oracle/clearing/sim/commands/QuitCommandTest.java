package com.oracle.clearing.sim.commands;


import com.oracle.clearing.sim.entities.Location;
import com.oracle.clearing.sim.entities.enums.Direction;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuitCommandTest {

    @Test
    public void quitCommandWorks() {

        QuitCommand quitCommand = new QuitCommand("test");

        Location currentLocation = new Location(0,0, Direction.EAST);

        List<Location> actual = quitCommand.apply(currentLocation);

        assertTrue(actual.isEmpty());
    }

}