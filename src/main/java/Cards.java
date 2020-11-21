//Name:Sathurshan Arulmohan
//Date: November 5, 2020
//Title: Cards
//Purpose: The class that will create the Cards object

import java.util.*;

public class Cards 
{
    //Variables
    private int CardTotal;
    private int intValue;
    private String Suit;
    private int intSuit;
    private String Type;
    
    //ADT -ARRAYLIST
    private ArrayList CardSuit = new ArrayList();
    private ArrayList CardType = new ArrayList();
        
    //Constructor 
    public Cards(int suit, int value)
    {
        intSuit = suit;
        intValue = value;
        
        //set the suit and vlaue string based on the integer input
        setSuitString();
        setValueString();
        setCardTotal();
    }
    
    //GETTERS
    public int getSuit() //get integer value of Suit
    {
        return intSuit;
    }
    public int getValue() //get integer value of Value
    {
        return intValue;
    }
    public int getCardTotal() //get the Total value of the card
    {
        return CardTotal;
    }
    public String getSuitString() //get the String of the Suit
    {
        return Suit;
    }
    public String getValueString() //get the String of the Value
    {
        return Type;
    }
    
    //SETTERS
    public void setSuitString()
    {
        //Add the suit types to List
        CardSuit.add("SPADES"); //0
        CardSuit.add("HEARTS"); //1
        CardSuit.add("DIAMONDS"); //2
        CardSuit.add("CLUBS"); //3
        
        //sets the String of suit based on the integer value
        Suit = CardSuit.get(intSuit).toString();
    }
    public void setCardTotal(int num) //sets the Card Total
    {
        CardTotal = num;
    }
    public void setValueString()//Determines the number/letter of the Card
    {
        //Add the Card Type to List
        CardType.add("ACE"); //0
        CardType.add("Two"); //1
        CardType.add("Three"); //2
        CardType.add("Four"); //3
        CardType.add("Five"); //4
        CardType.add("Six"); //5
        CardType.add("Seven"); //6
        CardType.add("Eight"); //7
        CardType.add("Nine"); //8
        CardType.add("Ten"); //9
        CardType.add("Jack"); //10
        CardType.add("Queen"); //11
        CardType.add("King"); //12
        
        //sets the String of the Card Type bases on the integer value
        Type = CardType.get(intValue).toString();
    }
    public void setCardTotal()//Gives Values to each card
    {
        //Number cards hold their own value
        CardTotal = intValue + 1;
        
        //Jack, Queen, King is 10
        if (Type.equals("Jack") || Type.equals("Queen") || Type.equals("King"))
        {
            CardTotal = 10;
        }
        //set ACE as 11, and it will be changed if Player decided to 
        if (Type.equals("ACE"))
        {
            CardTotal = 11;
        }
    }
    public void setAceValue(int num) //set the ACE value
    {
        CardTotal = num;
    }
    
    //Output
    public String toString()
    {
         return Type + " of " + Suit + ".\n";
    }
}
