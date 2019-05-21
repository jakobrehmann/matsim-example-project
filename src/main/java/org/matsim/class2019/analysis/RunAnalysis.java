package org.matsim.class2019.analysis;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.matsim.core.api.experimental.events.EventsManager;
import org.matsim.core.events.EventsUtils;
import org.matsim.core.events.MatsimEventsReader;

public class RunAnalysis {

	public static void main(String[] args) {
		Path events = Paths.get("C:\\Users\\jakob\\Dropbox\\Documents\\Education-TUB\\2019_SS\\MATSim\\UE5\\baseCase\\output_events_base.xml.gz");
		
		TravelTimeEventHandler handler = new TravelTimeEventHandler() ;
		EventsManager manager = EventsUtils.createEventsManager();
		manager.addHandler(handler);
		new MatsimEventsReader(manager).readFile(events.toString());
		double totalTravelTime = handler.computeOverallTravelTime() ;
		System.out.println("time = " + totalTravelTime) ;
		
	}

}
