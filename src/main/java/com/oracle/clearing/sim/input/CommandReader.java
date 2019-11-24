package com.oracle.clearing.sim.input;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class CommandReader {

    private static final Pattern QUIT_REGEX = Pattern.compile("(Quit|quit|q|Q)");
    private static final Pattern HELP_REGEX = Pattern.compile("(Help|help|h|H)");


    public Stream<String> enterCommand() {
        System.out.println("\nCurrent Location - Northern edge of the site, " +
                "immediately to the West of the site, and facing East.\n");
        System.out.println("Simulation started..... Please enter your instructions below or enter 'help' to get usage help ");


        Scanner scanner = new Scanner(System.in);

        List<String> result = new ArrayList<>();



        while (!scanner.hasNext(QUIT_REGEX)) {
           /* if (scanner.nextLine().matches(HELP_REGEX.toString())){
                System.out.print("Enter the instructions using the letters l:left, r:right, a<n>:Advance by n steps, q:quit:");
                break;
            }*/
            String x= scanner.nextLine();

             result.add(x.toUpperCase());

        }

        result.add("QUIT");

        result.forEach(System.out::println);

        return result.stream();
    }

}

