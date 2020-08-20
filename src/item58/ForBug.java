package item58;

import java.util.*;

public class ForBug {
    enum Suit {
        CLUB,
        DIAMOND,
        HEART,
        SPADE,
        ;
    }

    enum Rank {
        ACE,
        DEUCE,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING,
        ;
    }

    static class Card {
        Card(Suit suit, Rank rank) {

        }
    }

    static Collection<Suit> suits = Arrays.asList(Suit.values());
    static Collection<Rank> ranks = Arrays.asList(Rank.values());

    public static void main(String[] args) {
        List<Card> deck = new ArrayList<>();
//        for (Iterator<Suit> i = suits.iterator(); i.hasNext(); ) {
//            Suit suit = i.next();
//            for (Iterator<Rank> j = ranks.iterator(); j.hasNext(); ) {
////                deck.add(new Card(i.next(), j.next())); // i가 없는데 불려서 NoSuchElementException 불림
//                deck.add(new Card(suit, j.next())); // 위에서 지정한 suit 를 넣음
//            }
//        }

        for (Suit suit : suits) {
            for (Rank rank : ranks) {
                deck.add(new Card(suit, rank));
            }
        }
    }
}
