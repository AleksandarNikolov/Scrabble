package tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.Server.Model.Bag;

public class BagTest {
	
	private Bag bag;
	
	@BeforeEach
	public void setUp() {
		bag = new Bag();
	}
	
	@Test
	public void isEmpty() throws Exception{
		assertFalse(bag.isEmpty());
		int n = bag.getRemaining();
		for(int i = 0; i < n; i++) {
			assertFalse(bag.isEmpty());
			bag.pickUp();
		}
		assertTrue(bag.isEmpty());
	}
	
	@Test
	public void pickup() throws Exception {
		int nBefore = bag.getRemaining();
		bag.pickUp();
		int nAfter = bag.getRemaining();
		assertNotEquals(nBefore, nAfter);
	}
	
	
}
