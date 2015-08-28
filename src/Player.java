
import java.util.ArrayList;

/**
 * Created by saksham on 8/24/15.
 */
public class Player {
    
    private String playerName;
    private int totalCards = 21;
    private boolean hasMainCashCard = false;
    ArrayList<CardSet> playersHand;

    public Player(String name){
        this.playerName = name;
    }
    
    public String getName(){
        return this.playerName;
    }
    public void setHand(ArrayList<CardSet> playersHand){
        this.playersHand = playersHand;
    }

}
