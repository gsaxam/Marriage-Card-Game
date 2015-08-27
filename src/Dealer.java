import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by saksham on 8/24/15.
 */
public class Dealer {

    // Each deck has 52 + 1 JOKER. Total decks = 3
    int totalCards = 159;
    private int numberOfPlayers;
    private static ArrayList<CardSet> deck;
    private static ArrayList<CardSet> playersHand;


    //21 cards in marriage card game
    private int numOfCardsInPlayersHand = 21;

    /* retuns a list of type CardSet by initializing
    the CardSet class and calling its getNewDeck() method
     */
    public ArrayList<CardSet> getDeck(){

        CardSet cards = new CardSet(null,null);
        return cards.getNewDeck();

    }
    public int setNumberOfPlayers(int numOfPlayers){
        return this.numberOfPlayers = numOfPlayers;
    }
    public ArrayList<CardSet> shuffleCards(ArrayList<CardSet> allCards){

        Collections.shuffle(allCards);
        return allCards;

    }

    public ArrayList<CardSet> getPlayersHand(){

        return new ArrayList<>(deck.subList(0,numOfCardsInPlayersHand));
    }

    public void printHand(ArrayList<CardSet> hand) {
        int cursor = 0;
        while (cursor < hand.size()) {
            for (Object card : hand) {
                System.out.println(hand.get(cursor).suit() + "-" + hand.get(cursor).rank());
                cursor++;
            }
        }
    }

    public static void main(String [] args) throws IOException {

        Dealer dealer = new Dealer();
        //Marriage game is perfect with 5 players.
        dealer.setNumberOfPlayers(5);
        deck = dealer.getDeck();
        deck = dealer.shuffleCards(deck);

        playersHand = dealer.getPlayersHand();
        dealer.printHand(playersHand);

        String dirPath = "resources/";

        JLayeredPane tableMat = new JLayeredPane();
        int z_position =0;
        int x_offset = 15;
        for (CardSet card : playersHand) {

            String path = dirPath + card.suit().toString()+"-"+card.rank().toString()+".gif";
            File file = new File(path);

            if (!file.exists()) {
                System.out.println(path);
                throw new IllegalArgumentException("file " + file + " does not exist");


            } else {
                BufferedImage icon = ImageIO.read(new File(file.getAbsolutePath()));
                JLabel cardIcon = new JLabel(new ImageIcon(icon));
                cardIcon.setBounds(x_offset,20,300,300);
                tableMat.add(cardIcon, new Integer(z_position));
                z_position++;
                x_offset += 15;

            }

        }
        // define a frame to hold all panels.
        JFrame tableFrame = new JFrame("Marriage MultiPlayer");
        tableFrame.setContentPane(tableMat);
        tableFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tableFrame.setSize(800, 500);
        tableFrame.setVisible(true);




    }
}