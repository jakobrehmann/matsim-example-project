package org.matsim.advMATSim;

import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy;
import org.matsim.core.scenario.ScenarioUtils;

import static org.matsim.core.controler.OutputDirectoryHierarchy.OverwriteFileSetting.deleteDirectoryIfExists;

public class MyRunMatsim {
    public static void main(String[] args) {
//        Config config = ConfigUtils.createConfig();
        Config config = ConfigUtils.loadConfig("scenarios/equil/config.xml");

        config.controler().setLastIteration(2);
        config.controler().setOverwriteFileSetting(deleteDirectoryIfExists);

        Scenario scenario = ScenarioUtils.loadScenario(config);


        Controler controler = new Controler(scenario);

//        MyInterface myClass = MyUtils.createMyClass();



        controler.run();

    }
}
