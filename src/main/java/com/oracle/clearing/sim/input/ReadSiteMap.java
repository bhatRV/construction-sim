package com.oracle.clearing.sim.input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReadSiteMap implements Function<String, List<String>> {

    /**
     * Read the site structure from  a file
     * @param inputFileName
     * @return
     */
    @Override
    public List<String> apply(String inputFileName) {
        try{
           return Files.lines(Paths.get(inputFileName)).collect(Collectors.toList());

        } catch (IOException ioe) {
            ioe.printStackTrace();
            throw new RuntimeException(ioe.getMessage());
        }
    }

}
