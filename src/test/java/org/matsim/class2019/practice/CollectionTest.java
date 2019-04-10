package org.matsim.class2019.practice;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CollectionTest {
	
	@Test
	public void listExample() {
		
		List<Rectangle> myList = new ArrayList<>() ;
		myList.add(new Rectangle(1.0,2.0));
		myList.add(new Rectangle(3.0,4.0));
		myList.add(new Rectangle(5.0,6.0));
		
		for(Rectangle rect : myList) {
			System.out.println("Area: " + rect.calculateArea());
		}
	}
}
