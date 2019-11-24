package com.oracle.clearing.sim.commands;


import com.oracle.clearing.sim.entities.Location;

import java.util.ArrayList;
import java.util.List;

public class QuitCommand implements Command {

    private String reason;

    public QuitCommand(String reason) {
        this.reason = reason;
    }

    @Override
    public List<Location> apply(Location currentLocation) {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Execute QUIT";
    }

    public String getReason() {
        return reason;
    }
}
