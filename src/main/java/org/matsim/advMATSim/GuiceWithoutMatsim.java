package org.matsim.advMATSim;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.scenario.ScenarioUtils;

import static org.matsim.core.controler.OutputDirectoryHierarchy.OverwriteFileSetting.deleteDirectoryIfExists;

public class GuiceWithoutMatsim {

    public static void main(String[] args) {

        com.google.inject.Module module = new AbstractModule() { // from GOOGLE!!!!!!
            @Override
            protected void configure() {
                bind(SomeInterface.class ).to(MyImplementation.class);//interface to inplementation
                bind(Helper.class).to(MyHelperImp.class);
            }
        };
        Injector injector = Guice.createInjector(module);

        SomeInterface instance = injector.getInstance(SomeInterface.class);

        instance.doSomething();
    }
}
