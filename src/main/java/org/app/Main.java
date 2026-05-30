package org.app;

import org.app.functions.AstrologyCompatibilityService;

import org.app.functions.GetBirthChart;
import org.app.functions.birthChartService;



public class Main {
    static void main() {
        new birthChartService();
        new appLayout();

        int report = AstrologyCompatibilityService.calculateCompatibility("2004-08-13","2004-11-25");

        System.out.println(report);

    }
}
