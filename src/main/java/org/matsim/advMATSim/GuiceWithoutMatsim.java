package org.matsim.advMATSim;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class GuiceWithoutMatsim {

    public static void main(String[] args) {

        Module module = new AbstractModule() { // from GOOGLE!!!!!!
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
