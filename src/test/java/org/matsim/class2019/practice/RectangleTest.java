package org.matsim.class2019.practice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RectangleTest {
	
	@Test
	public void testCalculateArea() {
		Rectangle rec = new Rectangle(10.0, 5.0) ;
		double area = rec.calculateArea();
		System.out.println("The area is " + area);
		
		assertEquals(200.00, area, 0.0001) ;
		assertTrue(area>100);
		
	}
}
