package com.oracle.site.clearnce.sim;

import com.oracle.clearing.sim.commands.QuitCommand;
import com.oracle.clearing.sim.entities.Bulldozer;
import com.oracle.clearing.sim.entities.Location;
import com.oracle.clearing.sim.entities.Square;
import com.oracle.clearing.sim.entities.enums.Cost;
import com.oracle.clearing.sim.entities.enums.Direction;
import com.oracle.clearing.sim.excuters.CommandExecutor;
import com.oracle.clearing.sim.input.CommandParser;
import com.oracle.clearing.sim.input.CommandReader;
 import com.oracle.clearing.sim.cost.calc.CostCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {

    private Square[][] siteMap;
    private Bulldozer bulldozer;

    @BeforeEach
    public void setup() {
        siteMap = TestDataHelper.getSiteMap();
        bulldozer = new Bulldozer(new Location(0,0, Direction.EAST));
    }

    @Test
    public void testSuccess() {
        InputStream in = new ByteArrayInputStream("a 4\r\nr\r\na 4\r\nl\r\na 2\r\na 4\r\nl\r\nq\r\n".getBytes());
        System.setIn(in);

        Map<Cost, Integer> summaryOutputs = new HashMap<>();
        new CommandReader().enterCommand()
                .map(new CommandParser())
                .map(command -> new CommandExecutor(siteMap).apply(bulldozer, command))
                .filter(bulldozer1 -> bulldozer1.getLastCommand() instanceof QuitCommand)
                .findFirst().ifPresent(bulldozer1 -> {
            summaryOutputs.putAll(new CostCalculator(siteMap).apply(bulldozer1));
        });

        assertEquals(3, (int) summaryOutputs.get(Cost.COMMUNICATION_COST));
        assertEquals(8, (int) summaryOutputs.get(Cost.FUEL_COST));
        assertEquals(25, (int) summaryOutputs.get(Cost.UNCLEARED_SQUARE_COST));
        assertEquals(0, (int) summaryOutputs.get(Cost.DESTRUCTION_OF_PROTECTED_TREE_COST));
        assertEquals(0, (int) summaryOutputs.get(Cost.PAINT_DAMAGE_COST));
    }

    @Test
    public void testOutOfBoundary() {

        InputStream in = new ByteArrayInputStream("a 4\r\nr\r\na 5\r\nl\r\na 2\r\na 4\r\nl\r\nq\r\n".getBytes());
        System.setIn(in);

        Map<Cost, Integer> summaryOutputs = new HashMap<>();
        new CommandReader().enterCommand()
                .map(new CommandParser())
                .map(command -> new CommandExecutor(siteMap).apply(bulldozer, command))
                .filter(bulldozer1 -> bulldozer1.getLastCommand() instanceof QuitCommand)
                .findFirst().ifPresent(bulldozer1 -> {
                    summaryOutputs.putAll(new CostCalculator(siteMap).apply(bulldozer1));
                });

        assertEquals(3, (int) summaryOutputs.get(Cost.COMMUNICATION_COST));
        assertEquals(8, (int) summaryOutputs.get(Cost.FUEL_COST));
        assertEquals(25, (int) summaryOutputs.get(Cost.UNCLEARED_SQUARE_COST));
        assertEquals(0, (int) summaryOutputs.get(Cost.DESTRUCTION_OF_PROTECTED_TREE_COST));
        assertEquals(0, (int) summaryOutputs.get(Cost.PAINT_DAMAGE_COST));
    }

    @Test
    public void testProtectedTree() {
        InputStream in = new ByteArrayInputStream("a 8\r\nr\r\na 4\r\nq\r\n".getBytes());
        System.setIn(in);

        Map<Cost, Integer> summaryOutputs = new HashMap<>();
        new CommandReader().enterCommand()
                .map(new CommandParser())
                .map(command -> new CommandExecutor(siteMap).apply(bulldozer, command))
                .filter(bulldozer1 -> bulldozer1.getLastCommand() instanceof QuitCommand)
                .findFirst().ifPresent(bulldozer1 -> {
            summaryOutputs.putAll(new CostCalculator(siteMap).apply(bulldozer1));
        });

        assertEquals(3, (int) summaryOutputs.get(Cost.COMMUNICATION_COST));
        assertEquals(16, (int) summaryOutputs.get(Cost.FUEL_COST));
        assertEquals(21, (int) summaryOutputs.get(Cost.UNCLEARED_SQUARE_COST));
        assertEquals(0, (int) summaryOutputs.get(Cost.DESTRUCTION_OF_PROTECTED_TREE_COST));
        assertEquals(2, (int) summaryOutputs.get(Cost.PAINT_DAMAGE_COST));
    }

    @Test
    public void testRevisiting() {
        InputStream in = new ByteArrayInputStream("a 4\r\nr\r\na 4\r\nr\r\nr\r\na 4\r\nq".getBytes());
        System.setIn(in);

        Map<Cost, Integer> summaryOutputs = new HashMap<>();
        new CommandReader().enterCommand()
                .map(new CommandParser())
                .map(command -> new CommandExecutor(siteMap).apply(bulldozer, command))
                .filter(bulldozer1 -> bulldozer1.getLastCommand() instanceof QuitCommand)
                .findFirst().ifPresent(bulldozer1 -> {
            summaryOutputs.putAll(new CostCalculator(siteMap).apply(bulldozer1));
        });

        assertEquals(0, (int) summaryOutputs.get(Cost.PAINT_DAMAGE_COST));
        assertEquals(3, (int) summaryOutputs.get(Cost.COMMUNICATION_COST));
        assertEquals(8, (int) summaryOutputs.get(Cost.FUEL_COST));
        assertEquals(25, (int) summaryOutputs.get(Cost.UNCLEARED_SQUARE_COST));
        assertEquals(0, (int) summaryOutputs.get(Cost.DESTRUCTION_OF_PROTECTED_TREE_COST));
        assertEquals(0, (int) summaryOutputs.get(Cost.PAINT_DAMAGE_COST));
    }

    @Test
    public void testBadCommand() {
        InputStream in = new ByteArrayInputStream("a 4\r\nr\r\na\r\na 4\r\nl\r\na 2\r\na 4\r\nl\r\nq\r\n".getBytes());
        System.setIn(in);

        Map<Cost, Integer> summaryOutputs = new HashMap<>();
        new CommandReader().enterCommand()
                .map(new CommandParser())
                .map(command -> new CommandExecutor(siteMap).apply(bulldozer, command))
                .filter(bulldozer1 -> bulldozer1.getLastCommand() instanceof QuitCommand)
                .findFirst().ifPresent(bulldozer1 -> {
            summaryOutputs.putAll(new CostCalculator(siteMap).apply(bulldozer1));
        });

        assertEquals(4, (int) summaryOutputs.get(Cost.COMMUNICATION_COST));
        assertEquals(8, (int) summaryOutputs.get(Cost.FUEL_COST));
        assertEquals(25, (int) summaryOutputs.get(Cost.UNCLEARED_SQUARE_COST));
        assertEquals(0, (int) summaryOutputs.get(Cost.DESTRUCTION_OF_PROTECTED_TREE_COST));
        assertEquals(0, (int) summaryOutputs.get(Cost.PAINT_DAMAGE_COST));
    }

    @Test
    public void testAdvanceCommandEdgeCase() {
        InputStream in = new ByteArrayInputStream("a 4\r\nr\r\na 2\r\na 0\r\nl\r\nq\r\n".getBytes());
        System.setIn(in);

        Map<Cost, Integer> summaryOutputs = new HashMap<>();
        new CommandReader().enterCommand()
                .map(new CommandParser())
                .map(command -> new CommandExecutor(siteMap).apply(bulldozer, command))
                .filter(bulldozer1 -> bulldozer1.getLastCommand() instanceof QuitCommand)
                .findFirst().ifPresent(bulldozer1 -> {
            summaryOutputs.putAll(new CostCalculator(siteMap).apply(bulldozer1));
        });

        assertEquals(5, (int) summaryOutputs.get(Cost.COMMUNICATION_COST));
        assertEquals(8, (int) summaryOutputs.get(Cost.FUEL_COST));
        assertEquals(25, (int) summaryOutputs.get(Cost.UNCLEARED_SQUARE_COST));
        assertEquals(0, (int) summaryOutputs.get(Cost.DESTRUCTION_OF_PROTECTED_TREE_COST));
        assertEquals(0, (int) summaryOutputs.get(Cost.PAINT_DAMAGE_COST));
    }
}