package com.oracle.clearing.sim.commands;


import com.oracle.clearing.sim.entities.Location;

import java.util.List;

/**
 * Commands interface for all the commands to be run.
 */
public interface Command {

    List<Location> apply(Location currentLocation);
}
