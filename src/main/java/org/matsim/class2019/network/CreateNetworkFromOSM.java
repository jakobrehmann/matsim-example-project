package org.matsim.class2019.network;

import org.matsim.api.core.v01.network.Network;
import org.matsim.core.network.NetworkUtils;
import org.matsim.core.network.algorithms.NetworkCleaner;
import org.matsim.core.network.io.NetworkWriter;
import org.matsim.core.utils.geometry.CoordinateTransformation;
import org.matsim.core.utils.geometry.transformations.TransformationFactory;
import org.matsim.core.utils.io.OsmNetworkReader;


import java.nio.file.Path;
import java.nio.file.Paths;


public class CreateNetworkFromOSM {
	
	private static Path inputFile = Paths.get("C:\\Users\\jakob\\Dropbox\\Documents\\Education-TUB\\2019_SS\\MATSim\\UE3\\thueringen-latest.osm");
	private static String epsg = "EPSG:25832" ;
	
	
	public static void main(String[] args) {
		new CreateNetworkFromOSM().create();
	}
	
	public void create() {
		
		// create empty network container
		
		Network network = NetworkUtils.createNetwork();
		
		// cord transformation
		// from coord system --> to coord system
		CoordinateTransformation transformation = TransformationFactory.getCoordinateTransformation(
				TransformationFactory.WGS84,  epsg) ;
		
		// should the link be included, based on the coord and the hierarchy
		
		OsmNetworkReader reader = new OsmNetworkReader(network, transformation, true, true) ;
		
		
		//lambda method, body of method comes after ->
		reader.addOsmFilter((coord, hierarchy) -> {
		
			return hierarchy <= 4;
		});
		
		// 1: highway
		// 2: trunks of highways
		// 3: primary roads
		// 4: trunks of primary roads

		
		reader.parse(inputFile.toString());
		
		new NetworkCleaner().run(network) ; // removes disconnected network parts to avoid stuck agents
		
		
		//write network as xml 
		new NetworkWriter(network).write(Paths.get("C:\\Users\\jakob\\Dropbox\\Documents\\Education-TUB\\2019_SS\\MATSim\\UE3\\test-network.xml.gz").toString()) ;
		
	}
}




