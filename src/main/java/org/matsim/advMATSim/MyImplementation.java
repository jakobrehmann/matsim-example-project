package org.matsim.advMATSim;


import com.google.inject.Inject;

public class MyImplementation implements SomeInterface {

    @Inject Helper helper ;

    @Override
    public void doSomething() {
        System.out.println("Hello");
//        helper.ge

    }
}
