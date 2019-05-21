package org.matsim.class2019.configuringMATSim;

import org.matsim.api.core.v01.TransportMode;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.config.groups.ControlerConfigGroup.RoutingAlgorithmType;
import org.matsim.core.config.groups.QSimConfigGroup.TrafficDynamics;
import org.matsim.core.config.groups.StrategyConfigGroup;
import org.matsim.core.config.groups.StrategyConfigGroup.StrategySettings;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy.OverwriteFileSetting;
import org.matsim.core.replanning.strategies.DefaultPlanStrategiesModule;

public class ConfigEx {

	public static void main(String[] args) {
		Config config = ConfigUtils.createConfig() ;
		config.network().setInputFile("abc") ;
		//config.controler().setOutputDirectory("/abc" + System.nanotime() + "/");
		
		//global
		config.global().setCoordinateSystem("epsg:25833");
		
		config.global().setRandomSeed(0); // if random seed is same, the results will be the same

		//	config.global().setRandomSeed(System.currentTimeMillis()); // different results each time
		
		
		//QSim
		config.qsim().setTrafficDynamics(TrafficDynamics.kinematicWaves); // more realistic
		//queue, all cars will all go at the same time, no reaction time
		
		config.qsim().setStuckTime(10); // after how much time, if a stuck vehicle moved even if the next link if full, 10 seconds default
		
		
		// Controler
		
		config.controler().setCreateGraphs(true);
		
		// takes enums, not string, kinda in wrong place
		config.controler().setRoutingAlgorithmType(RoutingAlgorithmType.Dijkstra);
		// if there is already an output file, what to do (can program your self too)
		config.controler().setOverwriteFileSetting(OverwriteFileSetting.deleteDirectoryIfExists);
		
		
		// STRATEGY Set Replanning Strategy
		{
		StrategyConfigGroup.StrategySettings stratSets = new StrategyConfigGroup.StrategySettings() ;
		stratSets.setStrategyName(DefaultPlanStrategiesModule.DefaultStrategy.SubtourModeChoice);
		stratSets.setWeight(0.2);
		config.strategy().addStrategySettings(stratSets);
		// ---
		String[] modes = {TransportMode.car, TransportMode.bike} ;
		config.subtourModeChoice().setModes(modes);
		String[] chains = { TransportMode.car, TransportMode.bike};
		config.subtourModeChoice().setChainBasedModes(chains);
		}
		{
		StrategyConfigGroup.StrategySettings stratSets = new StrategyConfigGroup.StrategySettings() ;
		stratSets.setStrategyName(DefaultPlanStrategiesModule.DefaultSelector.ChangeExpBeta);
		stratSets.setWeight(0.8);
		config.strategy().addStrategySettings(stratSets);
		}
		{
			StrategyConfigGroup.StrategySettings stratSets = new StrategyConfigGroup.StrategySettings() ;
			stratSets.setStrategyName(DefaultPlanStrategiesModule.DefaultStrategy.ReRoute);
			stratSets.setWeight(0.2);
			config.strategy().addStrategySettings(stratSets);
		}
		
		//Turn of Innovation Modules
		// If 100 Iterations, turns off after 80
		config.strategy().setFractionOfIterationsToDisableInnovation(0.8); 
		
		
		

	}

}
