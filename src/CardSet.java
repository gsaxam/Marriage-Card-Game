import javax.smartcardio.Card;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by saksham on 8/24/15.
 */
public class CardSet {

    private static final List<CardSet> deck  = new ArrayList<CardSet>();

    static {
        for(Suits suit : Suits.values()){
            for (Ranks rank : Ranks.values()){
                deck.add(new CardSet(suit,rank));
            }
        }
    }

    private final Ranks rank;
    private final Suits suit;
    private int numOfDecks;

    public CardSet(Suits suit, Ranks rank){
        this.rank = rank;
        this.suit = suit;
    }

    public static void main (String [] args){
        CardSet cards = new CardSet(null, null);
        List<CardSet> hand = cards.getNewDeck();
        int cursor = 0;
        System.out.println(hand.size());
        while (cursor < hand.size()) {
            for (Object card : hand) {
                System.out.println(hand.get(cursor).suit + "-" + hand.get(cursor).rank);
                cursor ++;
            }
        }
    }

    public int getNumOfDecks(){
        return this.numOfDecks;
    }

    public List<CardSet> getNewDeck(){
        return new ArrayList<>(deck);
    }

    // Create ENUM for both Suites and Ranks
    public enum Ranks {TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE}
    public enum Suits {SPADES, HEARTS, CLUBS, DIAMONDS}

}
