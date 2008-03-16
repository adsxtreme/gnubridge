package org.gnubridge.core;

import java.util.ArrayList;
import java.util.List;

import org.gnubridge.core.deck.Color;

public class Player {

	public static final int WEST = 0;

	public static final int NORTH = 1;

	public static final int EAST = 2;

	public static final int SOUTH = 3;

	public static final int WEST_EAST = 0;
	public static final int NORTH_SOUTH = 1;
	
	private int direction;

	private List<Card> hand;

	private List<Card> played;

	private List<Trick> tricks;

//	private Direction direction2;
//
//	private Hand openingHand;


	
	public Player(int i) {
		hand = new ArrayList<Card>();
		played = new ArrayList<Card>();
		tricks = new ArrayList<Trick>();
		direction = i;
	}
	
//	public Player (Direction d, Hand h) {
//		this(d.getValue());
//		direction2 = d;
//		hand.addAll(h.toList());
//		openingHand = h;
//	}

	public void init(String[]... valueSuits) {
		for (int i = 0; i < valueSuits.length; i++) {
			String[] values = valueSuits[i];
			for (int j = 0; j < values.length; j++) {
				hand.add(new Card(values[j], Color.list[i]));

			}
		}

	}
	
	public String toString() {
		return directionToString(direction);
		
	}

	public static String directionToString(int dir) {
		switch (dir) {
		case WEST:
			return "West";
		case NORTH:
			return "North";
		case EAST:
			return "East";
		case SOUTH:
			return "South";
		default:
			throw new RuntimeException("Player uninitialized");
		}
	}

	public void init(Card... cards) {
		for (int i = 0; i < cards.length; i++) {
			hand.add(cards[i]);
		}

	}
	public void init(List<Card> cards) {
		for (Card card:cards) {
			hand.add(card);
		}

	}
	
	public void init(Player other) {
		init(other.getHand());
        for (Card card : other.getPlayedCards()) {
        	played.add(card);
        }
	}	
	
	
	private List<Card> getPlayedCards() {
		return played;
	}

	public boolean hasUnplayedCard(Card c) {
		return contains(hand, c);

	}

	private boolean contains(List<Card> list, Card c) {
		for (Card current : list) {
			if (current.equals(c)) {
				return true;
			}
		}
		return false;
	}

	public int getUnplayedCardsCount() {
		return hand.size();
	}

	public int getDirection() {
		return direction;
	}

	public Card play(Trick trick) {
		return play(trick, getPossibleMoves(trick).size()-1);
		

	}

	public boolean hasPlayedCard(Card c) {
		return contains(played, c);
	}

	public int countTricksTaken() {
		return tricks.size();
	}

	public void addTrickTaken(Trick trick) {
		tricks.add(trick);

	}

	public List<Card> getPossibleMoves(Trick trick) {
		List<Card> matching = new ArrayList<Card>();
		List<Card> nonMatching = new ArrayList<Card>();
		for (Card card : hand) {
			if (card.getDenomination().equals(trick.getDenomination())) {
				matching.add(card);
			} else {
				nonMatching.add(card);
			}
		}
		if (matching.size() == 0) {
			return nonMatching;
		} else {
			return matching;
		}
	}

	public Card play(Trick trick, int moveIndex) {
		List<Card> moves = getPossibleMoves(trick);
		Card result = moves.get(moveIndex);
		played.add(result);
		hand.remove(hand.indexOf(result));
		return result;
		
	}

	public List<Card> getHand() {
		return hand;
	}
	
	public int pair() {
      return matchPair(direction);
	}
	
	public static int matchPair(int player) {
		int result;
		switch (player) {
		case Player.WEST:
		case Player.EAST:
			result = Player.WEST_EAST;
			break;
		case Player.NORTH:
		case Player.SOUTH:
			result = Player.NORTH_SOUTH;
			break;
		default:
			throw new RuntimeException("Unknown player: " + player);
		}
		return result;
	}

	public int otherPair() {
		return matchPair((direction + 1) % 4);
	}

	public Direction getDirection2() {
		return Direction.instance(direction);
	}

}
