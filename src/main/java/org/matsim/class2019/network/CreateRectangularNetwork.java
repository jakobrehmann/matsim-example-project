package org.matsim.class2019.network;

import java.util.HashSet;
import java.util.Set;

import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.TransportMode;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.network.NetworkFactory;
import org.matsim.api.core.v01.network.Node;
import org.matsim.core.network.NetworkUtils;

public class CreateRectangularNetwork {
	
	private Set<String> allowedModes = new HashSet<>() ;
	
	public CreateRectangularNetwork() {
		allowedModes.add(TransportMode.car) ;
		allowedModes.add(TransportMode.pt) ;
	}
	
	public Network createNetwork(double left, double right, double top, double bottom) {
			
		// create an empty network
		Network network = NetworkUtils.createNetwork();
		NetworkFactory factory = network.getFactory() ; // creates factory
		
		//nodes
		Node n0 = factory.createNode(Id.createNodeId(0), new Coord(left, top)) ;
		network.addNode(n0);
		Node n1 = factory.createNode(Id.createNodeId(1), new Coord(right, top));
		network.addNode(n1);
		Node n2 = factory.createNode(Id.createNodeId(2), new Coord(right, bottom)) ;
		network.addNode(n2);
		Node n3 = factory.createNode(Id.createNodeId(3), new Coord(left, bottom)) ;
		network.addNode(n3);
		
		//links
		Link link0 = factory.createLink(Id.createLinkId(0), n0, n1) ;
		addAttributes(link0) ;	
		network.addLink(link0);
		Link link1 = factory.createLink(Id.createLinkId(1), n1, n2) ;
		addAttributes(link1) ;	
		network.addLink(link1);
		Link link2 = factory.createLink(Id.createLinkId(2), n2, n3) ;
		addAttributes(link2) ;	
		network.addLink(link2);
		Link link3 = factory.createLink(Id.createLinkId(3), n3, n1) ;
		addAttributes(link3) ;	
		network.addLink(link3);
		
		return network;
		
	}
	
	private void addAttributes(Link link) {
		link.setCapacity(600);
		link.setFreespeed(4.5);
		link.setAllowedModes(allowedModes);
	}
	
	
}
