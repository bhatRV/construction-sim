package com.oracle.clearing.sim.excuters;

import com.oracle.clearing.sim.entities.Square;
import com.oracle.clearing.sim.entities.enums.Type;
import com.oracle.clearing.sim.exception.SimulationException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Loads the Site information into an array
 */
public class SiteMapExecutor implements Function<List<String>, Square[][]> {

    @Override
    public Square[][] apply(List<String> lines) throws SimulationException {

        Square[][] sqr = new Square[lines.size()][lines.get(0).length()];

        lines.forEach(s -> {
            IntStream.range(0, s.length())
                    .forEach(value -> System.out.print(s.toCharArray()[value] + " "));
            System.out.println();
        });

        IntStream.range(0, lines.size())
                .mapToObj(i -> {
                    char[] temp = lines.get(i).toCharArray();
                    IntStream.range(0, temp.length)
                            .mapToObj(j -> {
                                sqr[i][j] = new Square(Type.of(temp[j]));
                                return j;
                            }).collect(Collectors.toList());
                    return i;
                }).collect(Collectors.toList());

        return sqr;
    }

    public void validateSiteStructure(List<String> lines) throws SimulationException {

         //System.out.println("Site Compatiblity Check starts ");


        char r='r';
        char t='t';
        char o='o';
        char b='T';
        lines.forEach(s -> {
            int bound = s.length();
            for (int value = 0; value < bound; value++) {
                char eachChar=s.toCharArray()[value];
               if( eachChar!=r && eachChar!=t && eachChar!=o  && eachChar!=b)
               {
                   System.out.println("Invalid Site format due to: "+eachChar);
                   throw new SimulationException("Error::Invalid Site format. Provide a valid site format with right chars!");
               }
            }
         });
    System.out.println("Compatible Site for Simulation,Proceed....");
    }


}
