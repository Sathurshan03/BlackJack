//Name: Sathurshan Arulmohan
//Date: November, 4, 2020
//Title: A9_BlackJack_SathurshanA
//Purpose: To simulate an online version of BlackJack

//import libraries
import java.util.*;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BlackJack 
{
    //Global variables and Objects
    static Cards Card;
    static JMenu mMain;
    static JMenuItem miInstructions, miProjectInfo, miExit;
    static JRadioButton radOnePlayer, radTwoPlayer, radThreePlayer;
    static ButtonGroup PlayerSelect;
    static JButton btnStart;
    static JLabel lblTitle, lblPlayerSelect, lblPlayingBoard, lblBorderOne, 
            lblBorderTwo, lblDealer, lblPlayerOne, lblPlayerTwo, lblPlayerThree,
            lblCardsLeftTitle, lblCardsLeft, lblDealerTotal, lblPlayer1Total, 
            lblPlayer2Total, lblPlayer3Total,lblDealerScore, lblPScore1, lblPScore2, lblPScore3;
    static JTextArea txtDealer, txtPlayerOne, txtPlayerTwo, txtPlayerThree;
    static JPanel panelTop, panelCenter, panelBottom, panelStartInfo, panelPlayingBoard;
    static boolean blnPlayer1, blnPlayer2, blnPlayer3;
    static int intCardsLeft;
    
    //ADT
    static List<Cards> Cards = new ArrayList<Cards>();;
    static Stack<Cards> Deck = new Stack<Cards>();
    static TreeSet Totals = new TreeSet ();
    
    static Persons Dealer;
    static Persons PlayerOne;
    static Persons PlayerTwo;
    static Persons PlayerThree;
            
    public static void guiApp()
    {
        //Set up the frame window
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Set up the Menu
        JMenuBar menuBar =new JMenuBar(); 
        mMain = new JMenu("MENU");
        miInstructions = new JMenuItem("INSTRUCTIONS");
        miProjectInfo = new JMenuItem("PROJECT INFO");
        miExit = new JMenuItem("EXIT");
        
        //add Menu properties to the Menu
        mMain.add(miInstructions);
        mMain.add(miProjectInfo);
        mMain.add(miExit);
        
        //add menubar to the menubar
        menuBar.add(mMain);
        
        //0- Create the start Info panel
        panelStartInfo = new JPanel(new GridBagLayout());
        GridBagConstraints Row1 = new GridBagConstraints();
        GridBagConstraints Row2 = new GridBagConstraints();
        GridBagConstraints Row3 = new GridBagConstraints();
        
        lblTitle = new JLabel("BLACKJACK!");
        btnStart = new JButton("START");
        lblPlayerSelect = new JLabel("Select number of players: ");
        radOnePlayer = new JRadioButton("One Player", true);
        radTwoPlayer = new JRadioButton("Two Players");
        radThreePlayer = new JRadioButton("Three Players");
        
        //give fonts and colours to specific components
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setForeground(Color.BLUE);
        lblPlayerSelect.setForeground(Color.PINK);
        btnStart.setBackground(Color.GREEN);
        
        //customize each rows and coloumns of GridBagLayout
        Row1.anchor = GridBagConstraints.PAGE_START;
        Row1.gridx = 1;
        Row1.gridwidth = 2;
        Row1.insets = new Insets(0,0,20,0);
        
        Row2.gridy = 1;
        Row2.insets = new Insets(0,0,0,30);
        
        Row3.gridy = 2;
        Row3.gridx = 1;
        Row3.gridwidth = 2;
        Row3.insets = new Insets(20,0,0,0);
        
        //add radiobuttons to a group
        PlayerSelect = new ButtonGroup();
        PlayerSelect.add(radOnePlayer);
        PlayerSelect.add(radTwoPlayer);
        PlayerSelect.add(radThreePlayer);
        
        //add components to the panelStartInfo
        panelStartInfo.add(lblTitle,Row1);
        panelStartInfo.add(lblPlayerSelect, Row2);
        panelStartInfo.add(radOnePlayer, Row2);
        panelStartInfo.add(radTwoPlayer, Row2);
        panelStartInfo.add(radThreePlayer, Row2);
        panelStartInfo.add(btnStart,Row3);
        
        //1- Set up Top Panel
        panelTop = new JPanel(new BorderLayout());
        lblBorderOne = new JLabel("_________________________________________________________________________________________________");
        
        //set fonts and colours to specific components
        lblBorderOne.setForeground(Color.CYAN);
        
        //add components/panels to panelTop
        panelTop.add(panelStartInfo, BorderLayout.PAGE_START);
        panelTop.add(lblBorderOne, BorderLayout.CENTER);
        
        //2- Set up the Playing Board
        panelPlayingBoard = new JPanel(new GridBagLayout());
        GridBagConstraints BoardRow1 = new GridBagConstraints();
        GridBagConstraints BoardRow2 = new GridBagConstraints();
        GridBagConstraints total = new GridBagConstraints();
        GridBagConstraints score = new GridBagConstraints();
        lblDealer = new JLabel("DEALER");
        lblPlayerOne = new JLabel("PLAYER ONE");
        lblPlayerTwo = new JLabel("PLAYER TWO");
        lblPlayerThree = new JLabel("PLAYER THREE");
        txtDealer = new JTextArea(8,13);
        txtPlayerOne = new JTextArea(8,13);
        txtPlayerTwo = new JTextArea(8,13);
        txtPlayerThree = new JTextArea(8,13);
        lblDealerTotal = new JLabel("Total: --");
        lblPlayer1Total = new JLabel("Total: --");
        lblPlayer2Total = new JLabel("Total: --");
        lblPlayer3Total = new JLabel("Total: --");
        lblDealerScore = new JLabel("Score: 0");
        lblPScore1 = new JLabel("Score: 0");
        lblPScore2 = new JLabel("Score: 0");
        lblPScore3 = new JLabel("Score: 0");
        
        //Set up specific rows and colomns of the GridBadLayout
        BoardRow1.gridy = 0;
        
        BoardRow2.gridy = 1;
        BoardRow2.insets = new Insets(0,20,0,20);
        
        total.gridy = 2;
        
        score.gridy = 3;
        
        //add components to panelPlayingBoard
        panelPlayingBoard.add(lblDealer, BoardRow1);
        panelPlayingBoard.add(lblPlayerOne, BoardRow1);
        panelPlayingBoard.add(lblPlayerTwo, BoardRow1);
        panelPlayingBoard.add(lblPlayerThree, BoardRow1);
        panelPlayingBoard.add(txtDealer, BoardRow2);
        panelPlayingBoard.add(txtPlayerOne, BoardRow2);
        panelPlayingBoard.add(txtPlayerTwo, BoardRow2);
        panelPlayingBoard.add(txtPlayerThree, BoardRow2);
        panelPlayingBoard.add(lblDealerTotal, total);
        panelPlayingBoard.add(lblPlayer1Total, total);
        panelPlayingBoard.add(lblPlayer2Total, total);
        panelPlayingBoard.add(lblPlayer3Total, total);
        panelPlayingBoard.add(lblDealerScore, score);
        panelPlayingBoard.add(lblPScore1, score);
        panelPlayingBoard.add(lblPScore2, score);
        panelPlayingBoard.add(lblPScore3, score);
        
        //3- Set up Center panel
        panelCenter = new JPanel(new BorderLayout());
        lblPlayingBoard = new JLabel("Playing Board");
        
        //set up colours and fonts to specific components
        lblPlayingBoard.setForeground(Color.BLUE);
        
        //add components/panels to panelCenter
        panelCenter.add(lblPlayingBoard, BorderLayout.PAGE_START);
        panelCenter.add(panelPlayingBoard, BorderLayout.CENTER);
        
        //4- Set up Bottom panel
        panelBottom = new JPanel(new BorderLayout());
        lblBorderTwo = new JLabel("_________________________________________________________________________________________________");
        lblCardsLeftTitle = new JLabel("Cards Left: ");
        lblCardsLeft = new JLabel("52");
        
        //set up fonts and colours to specific components
        lblBorderTwo.setForeground(Color.CYAN);
        lblCardsLeftTitle.setForeground(Color.MAGENTA);
        lblCardsLeft.setForeground(Color.MAGENTA);
        
        //add components to panelBottom
        panelBottom.add(lblBorderTwo, BorderLayout.PAGE_START);
        panelBottom.add(lblCardsLeftTitle, BorderLayout.LINE_START);
        panelBottom.add(lblCardsLeft, BorderLayout.CENTER);
        
        //Create a new ButtonHandler instance
        ButtonHandler onClick = new ButtonHandler();
        btnStart.addActionListener(onClick);
        miExit.addActionListener(onClick);
        miInstructions.addActionListener(onClick);
        miProjectInfo.addActionListener(onClick);
        
        //add all panels to frame
        Container contentPane = frame.getContentPane();
        contentPane.add(panelTop, BorderLayout.PAGE_START);
        contentPane.add(panelCenter, BorderLayout.CENTER);
        contentPane.add(panelBottom, BorderLayout.PAGE_END);
        
        //Displays the frame with specific porperties
        frame.setJMenuBar(menuBar);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    //Mouse click handler
     private static class ButtonHandler implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            String command = e.getActionCommand();
            
            if(command.equals("START"))//START button
            {
                //instantiate the different players
                Dealer = new Persons("Dealer");
                PlayerOne = new Persons ("Player One");
                PlayerTwo = new Persons ("Player Two");
                PlayerThree = new Persons ("Player Three");
            
                //set up the Playing Board
                setPlayingBoard();
                
                //create the deck of cards and shuffle it 
                shuffle();
        
                //deal cards to each player
                dealCards();
            }
            else if (command.equals("INSTRUCTIONS")) //INSTRUCTIONS menu item
            {
                JOptionPane.showMessageDialog(null, "Choose the number of Players and then click \"START\" to begin the game.\n"
                + "Red slots idicates Non-Active slots, and green indicates playing slots. \nRead the pop - up messages to help run"
                + " the game.\nOnce the round is completed, a new round will start. \nOnce you start the game, you can only Exit when"
                + " the deck is finished."); 
            }
            else if (command.equals("PROJECT INFO")) //Project info menu item
            {
                JOptionPane.showMessageDialog(null, "Name: Sathurshan Arulmohan\n"
                        + "Date: November 7, 2020\n"
                        + "Title: A9_BlackJack_SathurshanA\n"
                        + "Purpose: To play a digital version of Black Jack since Casinos can not operate fully during COVID-19");
            }
            else //EXIT menu item
            {
                System.exit(0);
            }
        }
    }        
    public static void setPlayingBoard()
    {
        //Name: Sathurshan Arulmohan
        //Date: November 5, 2020
        //Title: setPlayingBoard
        //Purpose: To set the PlayingBoard based on the number of Players
        
        //clear the items in the different ADTs and objects
        Totals.clear();
        Dealer.clearHand();
        PlayerOne.clearHand();
        PlayerTwo.clearHand();
        PlayerThree.clearHand();
        
        //Erase all text in all TextAreas
        txtDealer.setText("");
        txtPlayerOne.setText("");
        txtPlayerTwo.setText("");
        txtPlayerThree.setText("");
        
        //Reset the Total Output
        lblDealerTotal.setText("Total: --");
        lblPlayer1Total.setText("Total: --");
        lblPlayer2Total.setText("Total: --");
        lblPlayer3Total.setText("Total: --");
        
        //Check which radiobutton is selected to see determined number of players
        if (radOnePlayer.isSelected()) //ONE PLAYER
        {
            //ACTIVE SLOTS
            lblDealer.setForeground(Color.green);
            lblDealerScore.setForeground(Color.orange);
            lblDealerTotal.setForeground(Color.green);
            
            lblPlayerOne.setForeground(Color.green);
            lblPScore1.setForeground(Color.orange);
            lblPlayer1Total.setForeground(Color.green);
            blnPlayer1 = true;
            
            //NON ACTIVE SLOTS
            lblPlayerTwo.setForeground(Color.red);
            txtPlayerTwo.append("SLOT NOT IN USE");
            lblPScore2.setForeground(Color.red);
            lblPlayer2Total.setForeground(Color.red);
            blnPlayer2 = false;
            
            lblPlayerThree.setForeground(Color.red);
            txtPlayerThree.append("SLOT NOT IN USE");
            lblPScore3.setForeground(Color.red);
            lblPlayer3Total.setForeground(Color.red);
            blnPlayer3 = false;
            
        }
        else if (radTwoPlayer.isSelected()) //TWO PLAYERS
        {
            //ACTIVE SLOTS
            lblDealer.setForeground(Color.green);
            lblDealerScore.setForeground(Color.orange);
            lblDealerTotal.setForeground(Color.green);
            
            lblPlayerOne.setForeground(Color.green);
            lblPScore1.setForeground(Color.orange);
            lblPlayer1Total.setForeground(Color.green);
            blnPlayer1 = true;
            
            lblPlayerTwo.setForeground(Color.green);
            lblPScore2.setForeground(Color.orange);
            lblPlayer2Total.setForeground(Color.green);
            blnPlayer2 = true;
            
            //NON ACTIVE SLOTS            
            lblPlayerThree.setForeground(Color.red);
            txtPlayerThree.append("SLOT NOT IN USE");
            lblPScore3.setForeground(Color.red);
            lblPlayer3Total.setForeground(Color.red);
            blnPlayer3 = false;
            
        }
        else if (radThreePlayer.isSelected()) //THREE PLAYERS
        {
            //ACTIVE SLOTS
            lblDealer.setForeground(Color.green);
            lblDealerScore.setForeground(Color.orange);
            lblDealerTotal.setForeground(Color.green);
            
            lblPlayerOne.setForeground(Color.green);
            lblPScore1.setForeground(Color.orange);
            lblPlayer1Total.setForeground(Color.green);
            blnPlayer1 = true;
            
            lblPlayerTwo.setForeground(Color.green);
            lblPScore2.setForeground(Color.orange);
            lblPlayer2Total.setForeground(Color.green);
            blnPlayer2 = true;
            
            lblPlayerThree.setForeground(Color.green);
            lblPScore3.setForeground(Color.orange);
            lblPlayer3Total.setForeground(Color.green);
            blnPlayer3=  true;
        }
    }
    public static void shuffle()
    {
        //Name: Sathurshan Arulmohan
        //Date: November 5, 2020
        //Title: shuffle
        //Purpose: to create a deck of cards and shuffle
        
        //Set the total number of cards in deck
        intCardsLeft = 52;
        
        //Clear all cards in the List and Deck
        Cards.clear();
        Deck.clear();
        
        //Create the 52 individual cards and store it in Cards ArrayList
        for (int i = 0; i < 4; i++) //Adds the four different Suits 0-Spades, 1-Heart, 2-Diamonds, 3-clubs
        {
            for (int j = 0; j < 13; j++) //Adds the 13 different Card Types 0-Ace, 1-Two, 2-Three, 3-Four, 4-Five, 5-Six, 6-Seven, 7-Eight, 8-nine, 9-ten, 10-Jack, 11-Queen, 12-King
            {
                //add it to the ARRAYLIST
                Cards.add(new Cards(i,j));
            }
        }
        
        //Shuffle - randomly pick a number and add that to the Stack ADT
        for (int m = 0; m < 52; m++) //loop 52 times - number of cards in playable deck
        {
            //randomly choose an index from Cards to take away
            int index = (int)(Math.random() * Cards.size());
            
            //add that card to the Shuffled deck
            Deck.push(Cards.get(index));
            
            //Remove that card from the Card list
            Cards.remove(index);
        }        
    }
    public static void dealCards()
    {
        //Name: Sathurshan Arulmohan
        //Date: November 6, 2020
        //Title: dealCards
        //Will deal out cards to each participants if needs
        
        //Deals out two cards to each participant to start game
        for (int i = 0; i < 2; i++)
        {
            dealDealer(i+1);
            dealPlayer1();
            
            if(blnPlayer2) //if player two is playing, deal cards
            {
                dealPlayer2();
            }
            if(blnPlayer3) //if player three is playing, deal cards
            {
                dealPlayer3();
            }
        }
        
        //Deal out cards if each player wants to HIT or STAND
        while(blnPlayer1 || blnPlayer2 || blnPlayer3)//loop if any of player is still Active
        {
            if (blnPlayer1) //Player One
            {
                //Ask Player One to Hit or Stand
                if (PlayerOne.HitorStand())
                {
                    dealPlayer1();
                }
                else 
                {
                    blnPlayer1 = false;
                }

            }
            if(blnPlayer2) //Player Two
            {
                //Ask Player Two to Hit or Stand
                if (PlayerTwo.HitorStand())
                {
                    dealPlayer2();
                }
                else 
                {
                   blnPlayer2 = false;
                }
            }
            if (blnPlayer3) //Player Three
            {
                //Ask Player Three to Hit or Stand
                if (PlayerThree.HitorStand())
                {
                    dealPlayer3();
                }
                else 
                {
                   blnPlayer3 = false;
                }
            }
        }
        
        //after all players choose stand, send to method that will determine if dealer needs to show his cards or not
        checkBust();
    }
    public static void dealDealer(int Round)
    {
        //Name: Sathurshan Arulmohan
        //Date: November 6, 2020
        //Title: dealDealer
        //Purpose: to deal out the cards to the dealer
        
        //check if deck is empty before handing out cards
        checkDeck();
        
        //output the card at the top of Deck
        if (Round == 2)//the second hard is hidden for the dealer
        {
            txtDealer.append("HIDDEN CARD\n");
        }
        else //output the card for rest of cards
        {
            txtDealer.append(Deck.peek().toString());
        }

        //Take the card from top of Deck and put it in Dealer's Hand
        Dealer.addCards(Deck.pop());

        //Output number of cards left in deck
        lblCardsLeft.setText(Integer.toString(--intCardsLeft));

        //Check if Dealer is above 21
        if (Dealer.checkSafe() == false)
        {
            lblDealer.setForeground(Color.red);
            txtDealer.append("\nBUSTED");
        }
    }
    public static void dealPlayer1()
    {
        //Name: Sathurshan Arulmohan
        //Date: November 6, 2020
        //Title: dealPlayer1
        //Purpose: to deal out the cards to player 1
        
        //check if deck is empty before handing out cards
        checkDeck();
        
        //output the card at the top of Deck
        txtPlayerOne.append(Deck.peek().toString());

        //put the card at the top of Deck into the Player One's Hand
        PlayerOne.addCards(Deck.pop());

        //If the card is an ACE, ask Player if it holds 1 or 11
        if (PlayerOne.getLastCard().getValueString().equals("ACE"))
        {
            PlayerOne.askAce();
        }

        //Output the results
        lblPlayer1Total.setText("Total: " + PlayerOne.getTotal());
        lblCardsLeft.setText(Integer.toString(--intCardsLeft));

        //check if Player One went busted
        if (PlayerOne.checkSafe() == false)
        {
            blnPlayer1 = false;
            lblPlayerOne.setForeground(Color.red);
            txtPlayerOne.append("\nBUSTED");
        }
    }
    public static void dealPlayer2()
    {
        //Name: Sathurshan Arulmohan
        //Date: November 6, 2020
        //Title: dealPlayer2
        //Purpose: to deal out the cards to player 2
        
        //check if deck is empty before handing out cards
        checkDeck();
        
        //output the card at the top of Deck
        txtPlayerTwo.append(Deck.peek().toString());

        //put the card at the top of Deck into the Dealer's Hand
        PlayerTwo.addCards(Deck.pop());

        //If the card is ACE, ask Player Two if it holds 1 or 11
        if (PlayerTwo.getLastCard().getValueString().equals("ACE"))
        {
            PlayerTwo.askAce();
        }

        //Output the results
        lblPlayer2Total.setText("Total: " + PlayerTwo.getTotal());
        lblCardsLeft.setText(Integer.toString(--intCardsLeft));

        //Check if Player Two had went busted
        if (PlayerTwo.checkSafe() == false)
        {
            blnPlayer2 = false;
            lblPlayerTwo.setForeground(Color.red);
            txtPlayerTwo.append("\nBUSTED");
        }
    }
    public static void dealPlayer3()
    {
        //Name: Sathurshan Arulmohan
        //Date: November 6, 2020
        //Title: dealPlayer3
        //Purpose: to deal out the cards to player 3
        
        //check if deck is empty before handing out cards
        checkDeck();
        
        //output the card at the top of Deck
        txtPlayerThree.append(Deck.peek().toString());

        //put the card at the top of Deck into the Dealer's Hand
        PlayerThree.addCards(Deck.pop());

        //If the card is ACE, ask Player Three if it holds 1 or 11
        if (PlayerThree.getLastCard().getValueString().equals("ACE"))
        {
            PlayerThree.askAce();
        }

        //Output the results
        lblPlayer3Total.setText("Total: " + PlayerThree.getTotal());
        lblCardsLeft.setText(Integer.toString(--intCardsLeft));

        //check if Player Three had went busted
        if (PlayerThree.checkSafe() == false)
        {
            blnPlayer3 = false;
            lblPlayerThree.setForeground(Color.red);
            txtPlayerThree.append("\nBUSTED");
        }
    }
    public static void checkBust()
    {
        //Name: Sathurshan Arulmohan
        //Date: November 6, 2020
        //Title: checkBust
        //Purpose: To see if all players went bust because Dealer does not reveal his second card if true
        
        //if atleast one player is under 21, dealer must reveal his second card
        if (PlayerOne.getTotal() <= 21 || PlayerTwo.getTotal() <= 21 || PlayerThree.getTotal() <= 21)
        {
            dealerPlay();
        }
        else //if all players have above 21, Dealer does not need to reveal second card 
        {
            checkWinner();
        }
    }
    public static void dealerPlay()
    {
        //Name: Sathurshan Arulmohan
        //Date: November 6, 2020
        //Title: dealerPlay
        //Purpose: to reveal Dealer's second card and to add cards to hand if necessary
        
        //Reveal Dealer's second card
        txtDealer.setText(Dealer.getHand());
        
        //Output the Total value of the two cards
        lblDealerTotal.setText("Total: " + Dealer.getTotal());
        
        //Keep adding cards to Dealer's hand if Dealer's total is under 17
        while(Dealer.getTotal() < 17)
        {
            //output message to user
            JOptionPane.showMessageDialog(null,"Dealer picks up another card");
            
            //Deal a card to dealer, -999 as parameter so it doesn;t trigger the second hide card feature
            dealDealer(-999);
            
            //Output total of Dealer's hand
            lblDealerTotal.setText("Total: " + Dealer.getTotal());
        }   
        
        //Check if Dealer went bust 
        checkWinner();
    }
    public static void checkWinner()
    {
        //Name: Sathurshan Arulmohan
        //Date: November 6, 2020
        //Title: checkWinner
        //Purpose: to check the winner of the round and give points 
        
        //Variables
        int hold;
        
        //add each participants total to TREE ADT, and TREE ADT will sort it out automatically 
        Totals.add(Dealer.getTotal());
        Totals.add(PlayerOne.getTotal());
        Totals.add(PlayerTwo.getTotal());
        Totals.add(PlayerThree.getTotal());
        
        if(Totals.lower(22) == null) //if all total above 21, that means everyone went bust and Dealer wins
        {
            //Add score to Dealer and output message
            Dealer.addScore();
            lblDealerScore.setText("Score: " + Dealer.getScore());
            JOptionPane.showMessageDialog(null, "Dealer gains a point");
        }
        else //will check the highest total directly under 22, if tie, both participants get score
        {
            if(Totals.lower(22).equals(Dealer.getTotal())) //if Dealer matches highest total under 22
            {
                Dealer.addScore();
                lblDealerScore.setText("Score: " + Dealer.getScore());
                JOptionPane.showMessageDialog(null, "Dealer gains a point");
            }
            if(Totals.lower(22).equals(PlayerOne.getTotal())) //if Player one matches highest total under 22
            {
                PlayerOne.addScore();
                lblPScore1.setText("Score: " + PlayerOne.getScore());
                JOptionPane.showMessageDialog(null, "Player One gains a point");
            }
            if(Totals.lower(22).equals(PlayerTwo.getTotal())) //if Player two matches highest total under 22
            {
                PlayerTwo.addScore();
                lblPScore2.setText("Score: " + PlayerTwo.getScore());
                JOptionPane.showMessageDialog(null, "Player Two gains a point");
            }
            if(Totals.lower(22).equals(PlayerThree.getTotal())) //if Player three matches highest total under 22
            {
                PlayerThree.addScore();
                lblPScore3.setText("Score: " + PlayerThree.getScore());
                JOptionPane.showMessageDialog(null, "Player Three gains a point");
            }
        }
        
        //Clear the PlayingBoard
        setPlayingBoard();
        
        //Deal out new cards from remaining of deck to keep the game going
        dealCards();
    }
    public static void checkDeck()
    {
        //Name: Sathurshan Arulmohan
        //Date: November 6, 2020
        //Title: checkDeck
        //Purpose: to check if the Deck is empty

        //Variables
        String Options[] = {"EXIT GAME", "SHUFFLE CARDS"};
        int result;
        
        //Process
        if (Deck.isEmpty()) //If the deck is empty
        {
            //Display a message with an option to exit game or shuffle cards to continue game
            result = JOptionPane.showOptionDialog(null, "The deck ran out of cards. Please choose to Exit or to Shuffle to continue playing.", "Deck Empty",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, Options, Options[1]);
            
            if(result == JOptionPane.YES_OPTION) //EXIT
            {
                System.exit(0);
            }
            else //SHUFFLE
            {
                shuffle();
            }
        }
    }
    //Main Method
    public static void main (String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                guiApp();
            }
        });
    }
}
