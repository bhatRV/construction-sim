package com.oracle.clearing.sim.commands;


import com.oracle.clearing.sim.entities.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * UNKnownCommand is used to handle unknown user inputs.
 */
public class UNKnownCommand implements Command {

  private final String commandString;

  /**
   * Handles bad request
   * @param commandString
   */
  public UNKnownCommand(String commandString) {
    this.commandString = commandString;
  }

  @Override
  public List<Location> apply(Location currentLocation) {
    System.out.println(commandString + " is an Invalid Command!");

    List<Location> path = new ArrayList<>();

    if (currentLocation != null) {

      path.add(currentLocation);

    }
    return path;
  }

  @Override
  public String toString() {
    return null;
  }
}
