package org.matsim.advMATSim;

import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.TransportMode;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.network.NetworkFactory;
import org.matsim.api.core.v01.network.Node;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.scenario.ScenarioUtils;

import java.util.HashSet;
import java.util.Set;

import static org.matsim.core.controler.OutputDirectoryHierarchy.OverwriteFileSetting.deleteDirectoryIfExists;

public class RunMatsim {


    public static void main(String[] args) {
        Config config = ConfigUtils.loadConfig("scenarios/equil/config.xml");

        config.controler().setLastIteration(2);
        config.controler().setOverwriteFileSetting(deleteDirectoryIfExists);

        Scenario scenario = ScenarioUtils.loadScenario(config);

//        for (Link link : scenario.getNetwork().getLinks().values()) {
//            if (link.getFreespeed() <= 20. / 3.6 && link.getCapacity() < 1000) {
//                link.setFreespeed(1.);
//            }
//        }

        for (Link link : scenario.getNetwork().getLinks().values()) {
            Set<String> set = new HashSet<>();
            set.add(TransportMode.car);
            set.add(TransportMode.pt);

            link.setAllowedModes(set);
        }

        NetworkFactory nf = scenario.getNetwork().getFactory();
        Coord coord = new Coord(300, 300);
        Id<Node> id = Id.createNodeId("additionalNode");
        Node node = nf.createNode(id, coord);
        scenario.getNetwork().addNode(node);

        Node nodeA = scenario.getNetwork().getNodes().get(Id.createNodeId("nodeAId")); // inline!!!

        Node nodeC = scenario.getNetwork().getNodes().get(Id.createNodeId("nodeCId"));

        Link link1 = nf.createLink(Id.createLinkId("additionalLink"), nodeA, nodeC);
        scenario.getNetwork().addLink(link1);


        Controler controler = new Controler(scenario);

        controler.run();

    }
}
