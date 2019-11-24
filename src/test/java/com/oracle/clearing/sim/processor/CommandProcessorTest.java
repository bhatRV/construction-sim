package com.oracle.clearing.sim.processor;

import com.oracle.site.clearnce.sim.TestDataHelper;
import com.oracle.clearing.sim.commands.*;
import com.oracle.clearing.sim.entities.Bulldozer;
import com.oracle.clearing.sim.entities.Location;
import com.oracle.clearing.sim.entities.Square;
import com.oracle.clearing.sim.entities.enums.Direction;
import com.oracle.clearing.sim.excuters.CommandExecutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandProcessorTest {

    private CommandExecutor commandExecutor;

    private Square[][] site;

    @BeforeEach
    public void setup() {
        site = TestDataHelper.getSiteMap();
        commandExecutor = new CommandExecutor(site);

    }

    @Test
    public void firstCommandWorks() {

        Command command = new AdvanceCommand(5);
        Bulldozer input = new Bulldozer(new Location(0,0, Direction.EAST));

        Bulldozer output = commandExecutor.apply(input, command);

        assertEquals(1, output.getCommandList().size());
        assertEquals(new Location(0, 4, Direction.EAST), output.getCurrentLocation());

    }

    @Test
    public void subsequentCommandWorks() {

        Command command = new AdvanceCommand(2);
        Bulldozer input = new Bulldozer(new Location(0,3, Direction.SOUTH));
        input.getCommandList().add(new AdvanceCommand(4));
        input.getCommandList().add(new RightCommand());
        input.setPlaced(true);

        Bulldozer output = commandExecutor.apply(input, command);

        assertEquals(3, output.getCommandList().size());
        assertEquals(new Location(2, 3, Direction.SOUTH), output.getCurrentLocation());

    }

    @Test
    public void subsequentQuitCommandWorks() {

        Command command = new QuitCommand("test");
        Bulldozer input = new Bulldozer(new Location(0,3, Direction.SOUTH));
        input.getCommandList().add(new AdvanceCommand(4));
        input.getCommandList().add(new RightCommand());
        input.setPlaced(true);

        Bulldozer output = commandExecutor.apply(input, command);

        assertEquals(3, output.getCommandList().size());
        assertEquals(new Location(0, 3, Direction.SOUTH), output.getCurrentLocation());

    }

    @Test
    public void subsequentNoopCommandWorks() {

        Command command = new UNKnownCommand("test");
        Bulldozer input = new Bulldozer(new Location(0,3, Direction.SOUTH));
        input.getCommandList().add(new AdvanceCommand(4));
        input.getCommandList().add(new RightCommand());
        input.setPlaced(true);

        Bulldozer output = commandExecutor.apply(input, command);

        assertEquals(3, output.getCommandList().size());
        assertEquals(new Location(0, 3, Direction.SOUTH), output.getCurrentLocation());

    }

}