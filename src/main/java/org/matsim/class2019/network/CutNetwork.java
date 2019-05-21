package org.matsim.class2019.network;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.network.NetworkWriter;
import org.matsim.core.network.NetworkUtils;
import org.matsim.core.network.io.MatsimNetworkReader;

public class CutNetwork {

	public static void main(String[] args) {
		Path inputNetwork= Paths.get("C:\\Users\\jakob\\Dropbox\\Documents\\Education-TUB\\2019_SS\\MATSim\\UE4\\test-network-erfurt.xml.gz");
		Path outputNetwork= Paths.get("C:\\Users\\jakob\\Dropbox\\Documents\\Education-TUB\\2019_SS\\MATSim\\UE4\\test-network-erfurt-test.xml.gz");
		Network network= NetworkUtils.createNetwork();
		new MatsimNetworkReader(network).readFile(inputNetwork.toString());
		network.getLinks().get(Id.createLinkId("16578")).setCapacity(0);
		network.getLinks().get(Id.createLinkId("16584")).setCapacity(0);
		//network.getLinks().get(Id.createLinkId("16584")).setAllowedModes(modes);
		//network.getLinks().remove(Id.createLinkId("16584")) ;
		//network.getLinks()
		new NetworkWriter(network).write(outputNetwork.toString());
	}

}
