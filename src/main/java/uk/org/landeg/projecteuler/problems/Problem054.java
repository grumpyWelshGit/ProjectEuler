package uk.org.landeg.projecteuler.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import uk.org.landeg.projecteuler.FileLoader;
import uk.org.landeg.projecteuler.ProblemDescription;

@Component
@Order(54)
@Slf4j
public class Problem054 implements ProblemDescription<Integer>{

	@Override
	public String getTask() {
		return "In the card game poker, a hand consists of five cards and are ranked, from lowest to highest";
	}

	@Override
	public String getDescribtion() {
		return "How many hands does Player 1 win?\n";
	}

	enum WinType {
		HI_CARD(1),
		PAIR(2),
		TWO_PAIR(3),
		THREE_OF_A_KIND(4),
		STRAIGHT(5),
		FLUSH(6),
		FULL_HOUSE(7),
		FOUR_OF_A_KIND(8);
		
		private WinType (final int score) {
			this.score = score;
		}
		
		private final int score;
		
		public int getScore() {
			return score;
		}
	}

	private static final Map<Character, Integer> RANKS = new HashMap<>();
	private static final Map<Character, Integer> SUITS = new HashMap<>();
	private static final Map<WinType, Integer> WIN_SCORES = new HashMap<>();
	
	static {
		RANKS.put('2', 1);
		RANKS.put('3', 2);
		RANKS.put('4', 3);
		RANKS.put('5', 4);
		RANKS.put('6', 5);
		RANKS.put('7', 6);
		RANKS.put('8', 7);
		RANKS.put('9', 8);
		RANKS.put('T', 9);
		RANKS.put('J', 10);
		RANKS.put('Q', 11);
		RANKS.put('K', 12);
		RANKS.put('A', 13);
		SUITS.put('S', 0);
		SUITS.put('D', 1);
		SUITS.put('C', 2);
		SUITS.put('H', 3);
		WIN_SCORES.put(WinType.HI_CARD, 1);
		WIN_SCORES.put(WinType.PAIR, 2);
		WIN_SCORES.put(WinType.TWO_PAIR, 3);
		WIN_SCORES.put(WinType.THREE_OF_A_KIND, 4);
		WIN_SCORES.put(WinType.STRAIGHT, 5);
		WIN_SCORES.put(WinType.FLUSH, 6);
		WIN_SCORES.put(WinType.FULL_HOUSE, 7);
		WIN_SCORES.put(WinType.FOUR_OF_A_KIND, 8);
	}


	@Override
	public Integer solve() {
		int count = 0;
		final List<String> lines = FileLoader.readLines("p054_poker.txt");
		Hand hand1;
		Hand hand2;
		for (String line : lines) {
			final String[] cards = line.split(" ");
			hand1 = new Hand();
			hand2 = new Hand();
			for (int idx = 0 ; idx < cards.length ; idx++) {
				Hand hand = (idx <= 4) ? hand1 : hand2;
				hand.getCards().add(new Card(cards[idx]));
			}
			log.debug("{} {}",hand1, hand2);
			checkHand(hand1);
			checkHand(hand2);
			final List<HandWin> wins1 = hand1.getWins();
			final List<HandWin> wins2 = hand2.getWins();
			Hand winner = null;
			do {
				int diff = wins1.get(0).compareTo(wins2.get(0));
				if (diff < 0) {
					winner = hand1;
					count++;
				} else if (diff > 0) {
					winner = hand2;
				} else {
					wins1.remove(0);
					wins2.remove(0);
				}
			} while (winner == null);
		}
		return count;

	}

	private void checkHand (final Hand hand) {
		Collections.sort(hand.getCards());
		final Map<Integer, Integer> rankFreq = new HashMap<>();
		final Map<Integer, Integer> suitFreq = new HashMap<>();
		for (Card card : hand.getCards()) {
			int rank = card.getRank();
			Integer currentFreq = rankFreq.get(rank);
			if (currentFreq == null) {
				currentFreq = 0;
			}
			Integer currentSuitFreq = suitFreq.get(rank);
			if (currentSuitFreq == null) {
				currentSuitFreq = 0;
			}
			rankFreq.put(card.getRank(), currentFreq + 1);
			suitFreq.put(card.getSuit(), currentSuitFreq + 1);
			hand.getWins().add(new HandWin(WinType.HI_CARD, card.getRank()));
		}

		boolean isStraight = true;
		int straightRank = 0;
		for (int idx = 1 ; idx < hand.getCards().size() ; idx++) {
			final int rank = hand.getCards().get(idx).getRank(); 
			if (rank == hand.getCards().get(idx -1).getRank() + 1) {
				straightRank = hand.getCards().get(idx).getRank(); 
			} else {
				isStraight = false;
				break;
			}
		}
		if (isStraight) {
			hand.getWins().add(new HandWin(WinType.STRAIGHT, straightRank));
		}

		for (Entry<Integer, Integer> entry : rankFreq.entrySet()) {
			if (entry.getValue() == 2) {
				hand.getWins().add(new HandWin(WinType.PAIR, entry.getKey()));
			}
			if (entry.getValue() == 3) {
				hand.getWins().add(new HandWin(WinType.THREE_OF_A_KIND, entry.getKey()));
			}
			if (entry.getValue() == 4) {
				hand.getWins().add(new HandWin(WinType.FOUR_OF_A_KIND, entry.getKey()));
			}
		}
		final List<HandWin> pairs = getWins(hand, WinType.PAIR);
		if (pairs.size() == 2) {
			hand.getWins().add(new HandWin(WinType.TWO_PAIR, 0));
		}

		if (pairs.size() == 1 && getWins(hand, WinType.THREE_OF_A_KIND).size() == 1) {
			hand.getWins().add(new HandWin(WinType.FULL_HOUSE, 0));
		}

		if (suitFreq.size() == 1) {
			hand.getWins().add(new HandWin(WinType.FLUSH, 0));
		}
		Collections.sort(hand.getWins());
	}

	static class Hand {
		final List<Card> cards = new ArrayList<>();
		final List<HandWin> wins = new ArrayList<>();

		public List<Card> getCards() {
			return cards;
		}

		public List<HandWin> getWins() {
			return wins;
		}

		@Override
		public String toString() {
			return "Hand [cards=" + cards + ", wins=" + wins + "]";
		}
		
	}

	static class Card implements Comparable<Card>{
		final int rank;
		final int suit;
		public Card (final String def) {
			log.trace("creating card from def {}" , def);
			this.rank = RANKS.get(def.charAt(0));
			this.suit = SUITS.get(def.charAt(1));
		}
		public Card(int rank, int suit) {
			super();
			this.rank = rank;
			this.suit = suit;
		}
		public int getRank() {
			return rank;
		}
		public int getSuit() {
			return suit;
		}
		@Override
		public String toString() {
			return "Card [rank=" + rank + ", suit=" + suit + "]";
		}
		@Override
		public int compareTo(Card o) {
			int diff = rank - o.getRank(); 
			if (diff == 0) {
				diff = suit - o.getSuit();
			}
			return diff;
		}
	}
	
	private List<HandWin> getWins (final Hand hand, WinType type) {
		final List<HandWin> wins = new ArrayList<>();
		for (HandWin win : hand.getWins()) {
			if (win.getWinType().equals(type)) {
				wins.add(win);
			}
		}
		return wins;
	}

	static class HandWin implements Comparable<HandWin>{
		private final WinType winType;
		private final int rank;

		public HandWin(WinType winType, int rank) {
			super();
			this.winType = winType;
			this.rank = rank;
		}
		
		public WinType getWinType() {
			return winType;
		}
		
		public int getRank() {
			return rank;
		}

		@Override
		public String toString() {
			return "HandWin [winType=" + winType + ", rank=" + rank + "]";
		}

		@Override
		public int compareTo(HandWin o) {
			int diff = o.winType.ordinal() - winType.ordinal();
			if (diff == 0) {
				diff = o.getRank() - getRank();
			}
			return diff;
		}
		
		@Override
		public boolean equals(Object obj) {
			boolean equal = false;
			if (obj != null && obj.getClass().equals(this.getClass())) {
				equal = ((HandWin) obj).getRank() == this.getRank() && ((HandWin) obj).getWinType().equals(this.getWinType());
			}
			return equal;
		}
	}

	interface Win {
		int getScore ();
	}
}
