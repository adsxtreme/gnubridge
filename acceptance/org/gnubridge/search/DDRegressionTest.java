package org.gnubridge.search;

import static org.gnubridge.core.Direction.*;
import static org.gnubridge.core.deck.Trump.*;

public class DDRegressionTest extends DoubleDummyScenarioTestCase {

	// source: http://www.doubledummy.net/

	public void testCoffin1() {
		given(SOUTH, "", "Q,5", "Q,9", "");
		given(WEST, "9,7", "K,8", "", "");
		given(NORTH, "", "7,4,2", "", "6");
		given(EAST, "8,5", "J,9", "", "");
		givenTrump(HEARTS);
		whenLeadBy(SOUTH);
		thenTricksWon(2);
	}

	public void testCoffin2() {
		given(SOUTH, "8", "J", "A,7,3", "");
		given(WEST, "J", "3", "K,8,5", "");
		given(NORTH, "Q,7", "", "J,9,2", "");
		given(EAST, "K,9,5,2", "", "Q", "");
		givenTrump(HEARTS);
		whenLeadBy(SOUTH);
		thenTricksWon(3);
	}

	public void testSidneyLenzMiniature() {
		given(SOUTH, "A,3", "", "", "A");
		given(WEST, "J,2", "A", "", "");
		given(NORTH, "Q,8", "", "", "7");
		given(EAST, "K,10", "", "A", "");
		givenTrump(SPADES);
		whenLeadBy(SOUTH);
		thenTricksWon(2);
	}

	public void testTedMullerFittingEndMiniature() {
		given(SOUTH, "Q", "", "A,Q", "");
		given(WEST, "K", "", "", "J,10");
		given(NORTH, "", "K,J", "", "A");
		given(EAST, "", "A", "9", "7");
		givenTrump(SPADES);
		whenLeadBy(EAST);
		thenTricksWon(2);
	}

}
