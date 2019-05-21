package org.matsim.class2019.network;

import org.locationtech.jts.geom.Geometry;
import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.network.Network;
import org.matsim.core.network.NetworkUtils;
import org.matsim.core.network.algorithms.NetworkCleaner;
import org.matsim.core.network.io.NetworkWriter;
import org.matsim.core.utils.geometry.CoordinateTransformation;
import org.matsim.core.utils.geometry.geotools.MGC;
import org.matsim.core.utils.geometry.transformations.TransformationFactory;
import org.matsim.core.utils.gis.ShapeFileReader;
import org.matsim.core.utils.io.OsmNetworkReader;
import org.opengis.feature.simple.SimpleFeature;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;


public class CreateNetworkFromOSM_erfurt {
	
	private static Path inputFile = Paths.get("C:\\Users\\jakob\\Dropbox\\Documents\\Education-TUB\\2019_SS\\MATSim\\UE3\\thueringen-latest.osm");
	private static String epsg = "EPSG:25832" ;
	private static Path filterShape = Paths.get("C:\\Users\\jakob\\Dropbox\\Documents\\Education-TUB\\2019_SS\\MATSim\\UE4\\erfurt_shape\\erfurt.shp");
	
	
	public static void main(String[] args) {
		new CreateNetworkFromOSM_erfurt().create();
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
		
		reader.addOsmFilter(new NetworkFilter(filterShape));
		
		//lambda method, body of method comes after ->
		//reader.addOsmFilter((coord, hierarchy) -> {
		
			//return hierarchy <= 4;
	//	});
		
		// 1: highway
		// 2: trunks of highways
		// 3: primary roads
		// 4: trunks of primary roads

		
		reader.parse(inputFile.toString());
		
		new NetworkCleaner().run(network) ; // removes disconnected network parts to avoid stuck agents
		
		
		//write network as xml 
		new NetworkWriter(network).write(Paths.get("C:\\Users\\jakob\\Dropbox\\Documents\\Education-TUB\\2019_SS\\MATSim\\UE4\\test-network-erfurt2.xml.gz").toString()) ;
		
	}
	


	/**
	 * Includes motorways and primary roads for the whole dataset. If roads are contained within the supplied shape, also
	 * secondary and residential roads are included
	 */
	private static class NetworkFilter implements OsmNetworkReader.OsmFilter {

		private final Collection<Geometry> geometries = new ArrayList<>();

		NetworkFilter(Path shapeFile) {
			for (SimpleFeature feature : ShapeFileReader.getAllFeatures(shapeFile.toString())) {
				geometries.add((Geometry) feature.getDefaultGeometry());
			}
		}

		@Override
		public boolean coordInFilter(Coord coord, int hierarchyLevel) {
			// hierachy levels 1 - 3 are motorways and primary roads, as well as their trunks
			if (hierarchyLevel <= 4) return true;

			// if coord is within the supplied shape use every street above level of tracks and cycle ways
			return hierarchyLevel <= 8 && containsCoord(coord);
		}

		private boolean containsCoord(Coord coord) {
			return geometries.stream().anyMatch(geom -> geom.contains(MGC.coord2Point(coord)));
		}
}
}




