//Name: Sathurshan Arulmohan
//Date: November 6, 2020
//Title: Person
//Purpose: To create the Person class so cards of each player can be organized 

import java.util.*;
import javax.swing.*;

public class Persons 
{
    //Variables
    private String Name;
    private int Total;
    private int Score;
    
    //ADT - LINKED LIST
    private List<Cards> PlayerCards = new LinkedList<Cards>();
    
    //ITERATOR
    private ListIterator<Cards> iKey;
    
    //Constructor
    public Persons(String name)
    {
        Name = name;
        Total = 999;
        Score = 0;
    }
    
    //GETTERS
    public int getTotal()
    {
        return Total;
    }
    public String getName()
    {
        return Name;
    }
    public int getScore()
    {
        return Score;
    }
    public boolean checkSafe() //check if person has went bust
    {
        if (Total > 21)
        {
            return false;
        }
        else 
        {
            return true;
        }
    }
    public String getHand() //Return all the Cards that Person has
    {
        iKey = PlayerCards.listIterator();
        String hand = "";
        
        //uses ITERATOR to go through each node
        while (iKey.hasNext())
        {
            hand += iKey.next().toString();
        }
        
        return hand;
    }
    public Cards getLastCard() //return the recent card that person got
    {
        return PlayerCards.get(PlayerCards.size() - 1);
    }
    
    //SETTER/METHODS
    public void addCards(Cards card) //add cards to the person's hand
    {   
        PlayerCards.add(card);
        
        //calculate the new total
        calculateTotal();
    }
    public void calculateTotal() //calculate the total values in Hand
    {
        iKey = PlayerCards.listIterator();
        int sum =0;
        
        //uses ITERATOR to go through each node to count the Total Value
        while(iKey.hasNext())
        {
            sum += iKey.next().getCardTotal();
        }
        
        Total = sum;
    }
    public void addScore() //add score to person's records
    {
        Score++;
    }
    public void clearHand() //Clear the Person's hand
    {
        PlayerCards.clear();
    }
    
    //BEHAVIOURS
    public void askAce() //returns if person wants ACE to be worth 1 or 11
    {   
        //Variables
        String Options[] = {"1","11"};
        int input;
        
        //Output a message box to ask user their input
        input = JOptionPane.showOptionDialog(null, Name + " got an ACE. Would you want this to value 1 or 11?", "ACE VALUE",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, Options, Options[1]);
        
        if (input == JOptionPane.YES_OPTION) //Player chose Ace to worth 1
        {
            getLastCard().setCardTotal(1);
        }
        
        //recalculate the total
        calculateTotal();
    }
    public boolean HitorStand() //returns if person wants to HIT or STAND
    {
        //Variables
        int result;
        String Option [] = {"HIT","STAND"};
        
        //Output a message box to ask user their input
        result = JOptionPane.showOptionDialog(null, Name + ", please choose to hit or to stand", Name,JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, Option, Option[1]);
        
        if (result == JOptionPane.YES_OPTION) //Player chose to Hit
        {
            return true;
        }
        else //Player chose to Stand
        {
            return false;
        }
    }
}