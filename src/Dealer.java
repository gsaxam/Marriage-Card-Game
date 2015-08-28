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
    
    public String resourcePath = "resources/";
    
    //tableMat holds all cards on the table.
    public static JLayeredPane tableMat = new JLayeredPane();

    /* retuns a list of type CardSet by initializing
    the CardSet class and calling its getNewDeck() method.
    The number of decks has to be specified. For marriage game 3 decks 
    are required.
     */
    public ArrayList<CardSet> getDeck(int numberOfDecks){
        ArrayList<CardSet> deck = new ArrayList<>();
        CardSet cards = new CardSet(null,null);
        for (int i=0; i<numberOfDecks; i++){
            deck.addAll(cards.getNewDeck());
        }
        return deck;
    }
    public int setNumberOfPlayers(int numOfPlayers){
        return this.numberOfPlayers = numOfPlayers;
    }
    public ArrayList<CardSet> shuffleCards(ArrayList<CardSet> allCards){

        Collections.shuffle(allCards);
        return allCards;

    }
    // Get cards for each hand from the deck
    public ArrayList<CardSet> getPlayersHand(){

        ArrayList<CardSet> playersHand = new ArrayList<>(deck.subList(0,numOfCardsInPlayersHand));
        for (CardSet card : playersHand){
            deck.remove(card);
        }
        return playersHand;
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
    public void layCardsOnTable(ArrayList<CardSet> playersHand, String playerName,Boolean showCards,
            int xOffset, int yOffset, int zPosition,int width, int height) throws IOException{
//        int z_position =0;
//        int x_offset = 15;
        JLabel playerTag = new JLabel(playerName);
        playerTag.setBounds(xOffset+20,yOffset-20,width,height);
        tableMat.add(playerTag);
        
        for (CardSet card : playersHand) {
            File file;
            String path = resourcePath + card.suit().toString()+"-"+card.rank().toString()+".gif";
            String maskedCardPath = resourcePath + "BLUE-VERTICAL.gif";
            if(showCards){
                file = new File(path);
            } else 
            {
                file = new File(maskedCardPath);
            }

            if (!file.exists()) {
                System.out.println(path);
                throw new IllegalArgumentException("file " + file + " does not exist");


            } else {
                BufferedImage icon = ImageIO.read(new File(file.getAbsolutePath()));
                JLabel cardIcon = new JLabel(new ImageIcon(icon));
                cardIcon.setBounds(xOffset,yOffset,width,height);
                tableMat.add(cardIcon, new Integer(zPosition));
                zPosition++;
                xOffset += 15;

            }

        }
    }
    public void drawTable(int width, int height){
        // define a frame to hold all panels.
        JFrame tableFrame = new JFrame("Marriage MultiPlayer");
        tableFrame.setContentPane(tableMat);
        tableFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tableFrame.setSize(width, height);
        tableFrame.setVisible(true);
    }

    public static void main(String [] args) throws IOException {

        Dealer dealer = new Dealer();
        //Marriage game is perfect with 5 players.
        dealer.setNumberOfPlayers(5);
        deck = dealer.getDeck(3);
        deck = dealer.shuffleCards(deck);
        
//        createAndServePlayer("Bikash",)
        
        Player player1 = new Player("Bikash");
        player1.setHand(dealer.getPlayersHand());
        dealer.layCardsOnTable(player1.playersHand,player1.getName(),false,15, 1, 0, 450, 200);
        
        Player player2 = new Player("Prabhu");
        player2.setHand(dealer.getPlayersHand());
        dealer.layCardsOnTable(player2.playersHand,player2.getName(),true,15, 100, 0, 450, 200);
        
        Player player3 = new Player("Saksham");
        player3.setHand(dealer.getPlayersHand());
        dealer.layCardsOnTable(player3.playersHand,player3.getName(),true,15, 200, 0, 450, 200);
        
        Player player4 = new Player("Nancy");
        player4.setHand(dealer.getPlayersHand());
        dealer.layCardsOnTable(player4.playersHand,player4.getName(),true,15, 300, 0, 450, 200);
        
        Player player5 = new Player("Niju");
        player5.setHand(dealer.getPlayersHand());
        dealer.layCardsOnTable(player5.playersHand,player5.getName(),true,15, 400, 0, 450, 200);
   
        //Finally setup the table
        dealer.drawTable(1000,600);
        



    }
}