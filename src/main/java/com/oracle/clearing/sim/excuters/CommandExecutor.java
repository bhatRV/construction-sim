package com.oracle.clearing.sim.excuters;

import com.oracle.clearing.sim.commands.AdvanceCommand;
import com.oracle.clearing.sim.commands.Command;
import com.oracle.clearing.sim.commands.QuitCommand;
import com.oracle.clearing.sim.entities.Bulldozer;
import com.oracle.clearing.sim.entities.Location;
import com.oracle.clearing.sim.entities.Square;
import com.oracle.clearing.sim.entities.enums.Activity;
import com.oracle.clearing.sim.entities.enums.Cost;
import com.oracle.clearing.sim.entities.enums.Type;
import com.oracle.clearing.sim.exception.SimulationException;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Applied commands on the Bulldozer, gets the list of next positions, validates and keeps count of cost-items.
 */
public class CommandExecutor implements BiFunction<Bulldozer, Command, Bulldozer> {

    private Square[][] siteMap;

    public CommandExecutor(Square[][] siteMap) {
        this.siteMap = siteMap;
    }

    @Override
    public Bulldozer apply(Bulldozer bulldozer, Command command){

        bulldozer.getCommandList().add(command);

        if (bulldozer.getCurrentLocation() != null && !(command instanceof QuitCommand)) {

            List<Location> path = command.apply(bulldozer.getCurrentLocation());

            if (!bulldozer.isPlaced()) { // when this is the first command
                path.add(0, bulldozer.getCurrentLocation());
                path.remove(path.size() - 1);
            }
            if (!path.isEmpty()) { //to check paint-scratch scenario
                bulldozer.setCurrentLocation(path.get(path.size() - 1));
            }

            if (command instanceof AdvanceCommand && !path.isEmpty()) {
                    bulldozer.setPlaced(true);
                    try {
                        process(path, bulldozer);
                    } catch (SimulationException e) {
                        bulldozer.getCommandList().add(new QuitCommand(e.getMessage()));
                    }
            }
            updateCostMap(bulldozer, Cost.COMMUNICATION_COST, 1);
        }

        return bulldozer;
    }

    private Bulldozer process(List<Location> locations, Bulldozer bulldozer) {
        locations.forEach(p -> {
            try {

                Square square = siteMap[p.getX()][p.getY()];

                if (square.getType() == Type.PROTECTED_TREE) {

                    updateCostMap(bulldozer, Cost.DESTRUCTION_OF_PROTECTED_TREE_COST, 1);

                    throw new SimulationException("\nSession ended because there is an attempt to "
                            + "remove a tree that is protected");
                }

                if (square.isCleared()) {

                    updateCostMap(bulldozer, Cost.FUEL_COST, Activity.VISITING_CLEAN_SQUARE.getFuelUsage());

                } else {

                    updateCostMap(bulldozer, Cost.FUEL_COST, square.getType().getRelevantActivity().getFuelUsage());

                    if (square.getType() == Type.TREE && !p.equals(bulldozer.getCurrentLocation())) {

                        updateCostMap(bulldozer, Cost.PAINT_DAMAGE_COST, 1);

                    }

                    square.setCleared(true);
                }
            } catch (ArrayIndexOutOfBoundsException e){

                throw new SimulationException("\nSession ended because there is an attempt to navigate "
                        + "beyond the boundaries of the site; ");
            }
        });

        return bulldozer;
    }

    private void updateCostMap(Bulldozer bulldozer, Cost costType, int i) {
        Map<Cost, Integer> costMap = bulldozer.getItemisedCostMap();
        int value = costMap.getOrDefault(costType, 0) + i;
        costMap.put(costType, value);
    }

}
