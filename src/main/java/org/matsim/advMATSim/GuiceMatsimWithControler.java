package org.matsim.advMATSim;

import com.google.inject.Inject;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.population.Person;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.AbstractModule;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.core.scoring.ScoringFunction;
import org.matsim.core.scoring.ScoringFunctionFactory;
import org.matsim.core.utils.io.IOUtils;
import org.matsim.examples.ExamplesUtils;

import java.net.URL;

public class GuiceMatsimWithControler {

    public static void main(String[] args) {
        URL url = IOUtils.extendUrl(ExamplesUtils.getTestScenarioURL("equil"), "config.xml");
        Config config = ConfigUtils.loadConfig(url);
//        Config config = ConfigUtils.createConfig();
        config.controler().setOverwriteFileSetting(OutputDirectoryHierarchy.OverwriteFileSetting.deleteDirectoryIfExists);
        Scenario scenario = ScenarioUtils.loadScenario(config);

        Controler controler = new Controler(scenario);

        controler.addOverridingModule(new AbstractModule() {
            @Override
            public void install() {
                this.bindScoringFunctionFactory().to(MyScoringFunctionFactory.class);
            }
        });

        controler.run();



    }


    private static class MyScoringFunctionFactory implements ScoringFunctionFactory {
        private final Config config;
        //        @Inject private Config config ;
        @Inject Scenario scenario ;

        @Inject
        MyScoringFunctionFactory(Config config ) {
            this.config = config;
        }

        @Override
        public ScoringFunction createNewScoringFunction(Person person) {
            throw new RuntimeException("not implemented");
        }
    }
}
