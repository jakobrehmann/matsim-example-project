package org.matsim.advMATSim.guice;

import com.google.inject.*;

public class guiceWithoutMatsim {


    public static void main(String[] args) {

        Module module = new AbstractModule(){
            @Override protected void configure(){
                bind( Abc.class).to(AbcImp1.class);
                bind(Helper.class).to(HelperImp1.class); // The implementation is easily switched if by just changing the bind. 
            }
        } ;

        Injector injector = Guice.createInjector(module); // Throw into the cloud

        Abc abc = injector.getInstance(Abc.class); // Pull back out
        abc.doSomething() ;

    }

    // above is your "matsim-script-in java"
    // below are some implementations in the matsim framework.

    private interface Abc{
        void doSomething() ;
    }

    private static class AbcImp1 implements Abc {
        @Inject
        Helper helper ; // In order to Inject, you need to first bind .
        @Override public void doSomething(){
            System.out.println("calling imp1");
        }
    }

    private interface Helper {

        void doHelp();

    }

    private static class HelperImp1 implements Helper {
        @Override
        public void doHelp() {
            System.out.println("calling helper1");

        }

    }



}
