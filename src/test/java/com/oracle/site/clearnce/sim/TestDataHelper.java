package com.oracle.site.clearnce.sim;

import com.oracle.clearing.sim.entities.Square;
import com.oracle.clearing.sim.excuters.SiteMapExecutor;
import com.oracle.clearing.sim.input.ReadSiteMap;

import java.util.List;

public class TestDataHelper {

    public static Square[][] getSiteMap() {
        List<String> lines = new ReadSiteMap().apply("input/SiteMap1.txt");
        return new SiteMapExecutor().apply(lines);
    }
}
