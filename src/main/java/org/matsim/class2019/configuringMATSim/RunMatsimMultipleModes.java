package org.matsim.class2019.configuringMATSim;

import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.config.groups.PlansCalcRouteConfigGroup.ModeRoutingParams;
import org.matsim.core.config.groups.StrategyConfigGroup;
import org.matsim.core.config.groups.StrategyConfigGroup.StrategySettings;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy;
import org.matsim.core.replanning.strategies.DefaultPlanStrategiesModule;

public class RunMatsimMultipleModes {

	public static void main(String[] args) {
		//String configfile = null ;
		Config config = ConfigUtils.loadConfig("C:\\Users\\jakob\\git\\matsim-example-project\\scenarios\\equil\\config.xml");
		
		config.controler().setOutputDirectory("./output/");
		config.controler().setOverwriteFileSetting(OutputDirectoryHierarchy.OverwriteFileSetting.deleteDirectoryIfExists);
		config.controler().setLastIteration(2);
		
		
		// Add multiple modes -- remember to create local variables usig autocomplete
		{
			StrategyConfigGroup.StrategySettings stratSets = new StrategyConfigGroup.StrategySettings() ;
			stratSets.setStrategyName(DefaultPlanStrategiesModule.DefaultStrategy.ChangeSingleTripMode);
			double probability = 1.0 ;
			stratSets.setWeight(probability);
			config.strategy().addStrategySettings(stratSets);
			
			String[] strings = {"car","scooter"} ;
			config.changeMode().setModes(strings);	
		}
		
		// Here we are adding a new mode, scooter
		ModeRoutingParams pars = new ModeRoutingParams();
		String mode = "scooter";
		pars.setMode(mode);
		Double teleportedModeFreespeedFactor = 1.0;
		pars.setTeleportedModeFreespeedFactor(teleportedModeFreespeedFactor);
		config.plansCalcRoute().addModeRoutingParams(pars);
		
		
		
		Scenario scenario = ScenarioUtils.loadScenario(config);
		Controler controler = new Controler(scenario);
		controler.run(); 

	}

}
