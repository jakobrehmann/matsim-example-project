package org.matsim.advMATSim.guice;

import com.google.inject.Inject;
import com.google.inject.Module;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.TransportMode;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.population.Leg;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.PlanElement;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.*;
import org.matsim.core.controler.corelisteners.ControlerDefaultCoreListenersModule;
import org.matsim.core.router.TripRouter;
import org.matsim.core.scenario.ScenarioByInstanceModule;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.core.utils.io.IOUtils;
import org.matsim.examples.ExamplesUtils;
import org.matsim.facilities.FacilitiesUtils;
import org.matsim.facilities.Facility;

import java.net.URL;
import java.util.List;

public class guiceMatsimWithoutControler {


    public static void main(String[] args) {

//        Module module = new AbstractModule(){
//            @Override protected void configure(){
//                bind( Abc.class).to(AbcImp1.class);
//                bind(Helper.class).to(HelperImp1.class); // The implementation is easily switched if by just changing the bind.
//            }
//        } ;
//
//        Injector injector = Guice.createInjector(module); // Throw into the cloud
//
//        Abc abc = injector.getInstance(Abc.class); // Pull back out
//        abc.doSomething() ;


        URL url = IOUtils.extendUrl(ExamplesUtils.getTestScenarioURL("equil"), "config.xml");
        Config config = ConfigUtils.loadConfig(url);
//        Config config = ConfigUtils.createConfig();
        config.controler().setOverwriteFileSetting(OutputDirectoryHierarchy.OverwriteFileSetting.deleteDirectoryIfExists);
        Scenario scenario = ScenarioUtils.loadScenario(config);
        Module module = new AbstractModule(){ // Module is from google, AbstractMod is from MATSim
            @Override public void install(){ // versus configure()
                install(new NewControlerModule());
                install(new ScenarioByInstanceModule(scenario));
                install(new ControlerDefaultCoreListenersModule());
                install(new ControlerDefaultsModule());
            }
        };




        com.google.inject.Injector injector = Injector.createInjector(config, module);

        TripRouter tripRouter = injector.getInstance(TripRouter.class);
        String mainMode = TransportMode.car;
        Link fromLink = scenario.getNetwork().getLinks().get(Id.createLinkId("1"));
        Link toLink = scenario.getNetwork().getLinks().get(Id.createLinkId("22"));

        Facility fromFacility = FacilitiesUtils.wrapLink(fromLink);

        Facility toFacility = FacilitiesUtils.wrapLink(toLink);


        double depTime = 8.*3600 ;
        Person person = null;
        List<? extends PlanElement> legs = tripRouter.calcRoute(mainMode, fromFacility, toFacility, depTime, person) ;


        for (PlanElement planElement : legs) {
            System.out.println(planElement.toString());

        }
//        for (PlanElement i : legs) {
//            if (i instanceof Leg) {
//                Leg leg = (Leg) i ;
//                System.out.println(leg.getMode() );
//                System.out.println(leg.getRoute().getStartLinkId() );
//                System.out.println(leg.getRoute().getEndLinkId() );
//            }
//        }



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
    private static class HelperImp2 implements Helper {
        @Override
        public void doHelp() {
            System.out.println("calling helper2");

        }

    }




}
