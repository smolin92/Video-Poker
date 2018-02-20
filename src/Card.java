//*****************************************
//Card.java
//
//Card class for video poker containing
//methods to determine suit and value
//
//by Stefanie Molin
//November 1, 2011
//*****************************************

public class Card implements Comparable<Card>{
	public int suit;
	public int value;
	public final int CLUBS=1;
	public final int DIAMONDS=2;
	public final int HEARTS=3;
	public final int SPADES=4;
		
	public Card(int s, int v){
		suit=s;
		value=v;
	}
	
	public int compareTo(Card other){
		if(this.value < other.value){
			return -1;
		}//value is less than other card
		else if(this.value > other.value){
			return 1;
		}//value is greater than other card
		else{
			if(this.suit < other.suit){
				return -1;
			}//suit is earlier alphabetically
			else if(this.suit > other.suit){
				return 1;
			}//suit is later alphabetically
			else{
				return 0;
			}//same card (should not happen in fair deck)
		}//compare the suit because the value is the same
	}

	public String getSuit(){
		if(suit==SPADES)
			return "spades";
		else if(suit==HEARTS)
			return "hearts";
		else if(suit==DIAMONDS)
			return "diamonds";
		else
			return "clubs";
	}//turns the value of suits into words so the player can understand
	
	public String getValue(){
		if(value==13)
			return "King";
		else if(value==12)
			return "Queen";
		else if(value==11)
			return "Jack";
		else if(value==1)
			return "Ace";
		else
			return Integer.toString(value);
	}//turns the value of certain cards (face cards) into strings for easier understanding
	
	public String toString(){
		String playingCard=getValue() + " of " + getSuit();
		return playingCard;
	}//returns the card as a unit with both the value and suit
}
