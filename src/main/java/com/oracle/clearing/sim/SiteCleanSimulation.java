package com.oracle.clearing.sim;

import com.oracle.clearing.sim.commands.QuitCommand;
import com.oracle.clearing.sim.entities.Bulldozer;
import com.oracle.clearing.sim.entities.Location;
import com.oracle.clearing.sim.entities.Square;
import com.oracle.clearing.sim.entities.enums.Direction;
import com.oracle.clearing.sim.exception.SimulationException;
import com.oracle.clearing.sim.input.CommandParser;
import com.oracle.clearing.sim.input.CommandReader;
import com.oracle.clearing.sim.input.ReadSiteMap;
import com.oracle.clearing.sim.excuters.CommandExecutor;
import com.oracle.clearing.sim.cost.calc.CostCalculator;
import com.oracle.clearing.sim.excuters.SiteMapExecutor;

import java.util.List;

public class SiteCleanSimulation {

    private static Square[][] siteMap;

    public static void main(String[] args) throws SimulationException {

        System.out.println("Site Clearing Simulation starting.... Current Site layout is :\n");
        List<String> lines = new ReadSiteMap().apply(args[0]);

        siteMap = new SiteMapExecutor().apply(lines);
        new SiteMapExecutor().validateSiteStructure(lines);

        Bulldozer bulldozer = new Bulldozer(new Location(0 , 0, Direction.EAST)
        );
        new CommandReader().enterCommand()
                .map(new CommandParser())
                .map(command -> new CommandExecutor(siteMap).apply(bulldozer, command))
                .filter(bulldozer1 -> bulldozer1.getLastCommand() instanceof QuitCommand)
                .findFirst().ifPresent(
                        bulldozer1 -> new CostCalculator(siteMap).apply(bulldozer1));
    }
}
