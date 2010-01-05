package org.gnubridge.presentation;

import java.util.ArrayList;
import java.util.List;

import org.gnubridge.core.Card;
import org.gnubridge.core.Direction;
import org.gnubridge.core.Deal;
import org.gnubridge.core.Player;
import org.gnubridge.core.deck.Color;

public class GameUtils {
	public static void initializeSingleColorSuits(Deal g) {
		initializeSingleColorSuits(g, 13);

	}

	public static void initializeSingleColorSuits(Deal g, int cardCount) {
		for (int i = Direction.WEST; i <= Direction.SOUTH; i++) {
			String[] spades = {};
			String[] hearts = {};
			String[] diamonds = {};
			String[] clubs = {};
			String[] currentHand = new String[cardCount];
			for (int j = 0; j < cardCount; j++) {
				currentHand[j] = Card.FullSuit[j];
			}
			switch (i) {
			case Direction.WEST:
				spades = currentHand;
				break;
			case Direction.NORTH:
				hearts = currentHand;
				break;
			case Direction.EAST:
				diamonds = currentHand;
				break;
			case Direction.SOUTH:
				clubs = currentHand;
				break;
			}

			g.getPlayer(i).init(spades, hearts, diamonds, clubs);
		}
		
	}
	
	public static void initializeRandom(Deal g, int cardCount) {
      initializeRandom(g.getPlayers(), cardCount);
		
	}
	
	public static void initializeRandom(List<Player> players, int cardCount) {
		List<Card> deck = buildDeck();
		for (Player player : players) {
			List<Card> hand = new ArrayList<Card>();
			for(int j=0; j < cardCount; j++) {
				hand.add(dealRandom(deck));
			}
			player.init(hand);
			
		}		
	}

	private static Card dealRandom(List<Card> deck) {
		int selection = (int) Math.floor(Math.random() * deck.size());
		Card card = deck.get(selection);
		deck.remove(selection);
		return card;
	}

	public static List<Card> buildDeck() {
		List<Card> result = new ArrayList<Card>();
		for (String value : Card.FullSuit) {
			for(Color denomination : Color.list) {
			  result.add(new Card(value, denomination));	
			}
		}
		return result;
	}
}
