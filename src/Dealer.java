import java.util.ArrayList;
import java.util.List;

/**
 * Created by saksham on 8/24/15.
 */
public class Dealer {

    // Each deck has 52 + 1 JOKER. Total decks = 3
    int totalCards = 159;

    public void populateHand(){

        //CardSet hand = new CardSet(3);

        //System.out.println(hand.getAllCards());

    }

    public static void main(String [] args){
        Dealer deal = new Dealer();
        deal.populateHand();
    }
}
