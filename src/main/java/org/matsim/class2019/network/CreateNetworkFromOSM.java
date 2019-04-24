package org.matsim.class2019.network;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.network.NetworkWriter;
import org.matsim.core.network.NetworkUtils;
import org.matsim.core.network.algorithms.NetworkCleaner;
import org.matsim.core.utils.geometry.CoordinateTransformation;
import org.matsim.core.utils.geometry.transformations.TransformationFactory;
import org.matsim.core.utils.io.OsmNetworkReader;

public class CreateNetworkFromOSM {
	
	private static Path inputFile = Paths.get("C:\\Users\\jakob\\Dropbox\\Documents\\Education-TUB\\2019_SS\\MATSim\\UE3\\thueringen-latest.osm");
	private static String epsg = "EPSG:25832" ;
	
	
	public static void main(String[] args) {
		new CreateNetworkFromOSM().create();
	}
	
	public void create() {
		
		// create empty network
		
		Network network = NetworkUtils.createNetwork();
		
		// cord transformation
		CoordinateTransformation transformation = TransformationFactory.getCoordinateTransformation(
				TransformationFactory.WGS84,  epsg) ;
		
		// should the link be included, based on the coord and the hierarchy
		
		OsmNetworkReader reader = new OsmNetworkReader(network, transformation, true, true) ;
		
		//lambda method, body of method comes after ->
		reader.addOsmFilter((coord, hierarchy) -> {
			return hierarchy <= 4;
		});

		
		reader.parse(inputFile.toString());
		
		new NetworkCleaner().run(network) ; // removes disconnected network parts to avoid stuck agents
		
		
		//write network as xml 
		new NetworkWriter(network).write(Paths.get("C:\\Users\\jakob\\Dropbox\\Documents\\Education-TUB\\2019_SS\\MATSim\\UE3\\test-network.xml.gz").toString()) ;
		
	
}}
