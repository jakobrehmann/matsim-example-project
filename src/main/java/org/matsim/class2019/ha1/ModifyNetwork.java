package org.matsim.class2019.ha1;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Id;

/* *********************************************************************** *
 * project: org.matsim.*
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2017 by the members listed in the COPYING,        *
 *                   LICENSE and WARRANTY file.                            *
 * email           : info at matsim dot org                                *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *   See also COPYING, LICENSE and WARRANTY file                           *
 *                                                                         *
 * *********************************************************************** */


import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.network.NetworkFactory;
import org.matsim.api.core.v01.network.Node;
import org.matsim.core.network.NetworkUtils;
import org.matsim.core.network.io.MatsimNetworkReader;
import org.matsim.core.network.io.NetworkWriter;

/**
 * @author  jbischoff
 * This provides an example script how to read a MATSim network and modify some values for each link.
 * In this case, we are reducing the capacity of each link by 50%.
 */

public class ModifyNetwork {
	static String inputFile = "C:\\Users\\jakob\\Dropbox\\Documents\\Education-TUB\\2019_SS\\MATSim\\HA1\\input\\be_5_network_with-pt-ride-freight.xml" ;
	
	
	public static void main(String[] args) {
		
		// read in the network
		Network network = NetworkUtils.createNetwork();

		
		new MatsimNetworkReader(network).readFile(inputFile);
		
		NetworkFactory factory = network.getFactory() ; // creates factory
		
		
		// S Halensee
		double x0 = 4587723.222315477 ;
		double y0 = 5818954.321443435 ;
		Node n0 = factory.createNode(Id.createNodeId("jr_node_0"), new Coord(x0, y0)) ;

		
		// U Adenauer Platz
		double x1 = 4588884.259735091 ;
		double y1 = 5819352.321299794 ;
		Node n1 = factory.createNode(Id.createNodeId("jr_node_1"), new Coord(x1, y1)) ;
		
		// Uhlandstr 
		
		
		network.addNode(n0);
		network.addNode(n1);
		
		Link link0 = factory.createLink(Id.createLinkId("pt_0000_link_0"), n0, n1) ;
		addAttributes(link0) ;	
		network.addLink(link0);
		Link link1 = factory.createLink(Id.createLinkId("pt_0000_link_1"), n1, n0) ;
		addAttributes(link1) ;	
		network.addLink(link1);
		
		
		// iterate through all links
//		for (Link l : network.getLinks().values()){
//			//get current capacity
//			double oldCapacity = l.getCapacity();
//			double newCapacity = oldCapacity / 2.0  ;
//			
//			//set new capacity
//			l.setCapacity(newCapacity);
//		}
		new NetworkWriter(network).write("C:\\Users\\jakob\\Dropbox\\Documents\\Education-TUB\\2019_SS\\MATSim\\HA1\\input\\be_5_network_with-pt-ride-freight_jakob.xml");
	}
	
	private static void addAttributes(Link link) {
		link.setCapacity(600);
		link.setFreespeed(4.5);
}

}


