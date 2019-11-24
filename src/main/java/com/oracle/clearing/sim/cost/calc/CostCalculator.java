package com.oracle.clearing.sim.cost.calc;

import com.oracle.clearing.sim.commands.QuitCommand;
import com.oracle.clearing.sim.entities.Bulldozer;
import com.oracle.clearing.sim.entities.Square;
import com.oracle.clearing.sim.entities.enums.Cost;
import com.oracle.clearing.sim.entities.enums.Type;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Calculates cost and displays.
 */
public class CostCalculator implements Function<Bulldozer, Map<Cost, Integer>> {

    private final Square[][] siteMap;

    public CostCalculator(Square[][] siteMap) {
        this.siteMap = siteMap;
    }

    @Override
    public Map<Cost, Integer> apply(Bulldozer bulldozer) {

        Map<Cost, Integer> integerCostMap = bulldozer.getItemisedCostMap();

        System.out.println("\n"+ ((QuitCommand) bulldozer.getLastCommand()).getReason() + "\n");
        System.out.println(bulldozer.getCommandList().stream().map(Objects::toString).filter(Objects::nonNull).collect(Collectors.joining(", ")));

       /* integerCostMap.put(Cost.UNCLEARED_SQUARE_COST, (int) Arrays.stream(siteMap)
                        .flatMap(Arrays::stream)
                        .filter(square -> !square.isCleared() && !square.getType().equals(Type.PROTECTED_TREE))
                        .count());*/
        integerCostMap.put(Cost.UNCLEARED_SQUARE_COST, (int) Arrays.stream(siteMap)
                .flatMap(Arrays::stream)
                 .filter(square -> !square.isCleared() && !square.getType().equals(Type.PROTECTED_TREE))
                 .count()
        );
        System.out.println("\nDetailed Cost for clearing this site:\n");
        System.out.printf("%-30s %20s %20s\n", "Item", "Quantity", "Cost");
        integerCostMap.forEach((key, value) -> {
            System.out.printf("%-30s %20d %20d\n", key.getName(),
                    value, key.getCredit() * value);
        });
        System.out.printf("%-30s\n", "--------------------");
        System.out.printf("%-30s %40d", "Total COST:", integerCostMap.entrySet().stream().
                mapToInt(value -> value.getKey().getCredit() * value.getValue()).sum());
        System.out.println();
        System.out.printf("%-30s\n", "----------------");


        return integerCostMap;
    }
}
