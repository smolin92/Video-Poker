//*****************************************
//Deck.java
//
//Class that creates the deck of 52 cards,
//shuffles, and deals them for video poker.
//
//by Stefanie Molin
//November 1, 2011
//*****************************************
public class Deck {
	private int top=-1;
	public final int CARDS_IN_DECK=52;
	public Card[] deck;
	
	public Deck(){
		deck=new Card[CARDS_IN_DECK];
		int count=0;
		while(count<deck.length){
			for(int i=0; i < deck.length/13; i++)
			{
				int s=i+1;
				for(int j=0; j < deck.length/4; j++){
					int v=j+1;
					deck[count]= new Card(s, v);
					count++;
				}//end nested for loop	
			}//end outer for loop
		}//end while loop
	}//end constructor
	
	public void shuffle(){
		for(int k=0; k<10000; k++){
			int firstIndex=(int)(Math.random()*52);
			int secondIndex=(int)(Math.random()*52);
			Card oneToSwitch=deck[firstIndex];
			Card twoToSwitch=deck[secondIndex];
			deck[firstIndex]= twoToSwitch;
			deck[secondIndex]= oneToSwitch;
		}
		top=-1;//resets the top of the deck to negative 1 so when it can be incremented each time in the deal
			   //method without causing an out of bounds exception.
		 //shuffle method randomly selects two locations and stores the values of those cards in local variables
		 //it then assigns the those value into the alternate locations and vice versa.  It switches cards in
		 //this manner for 10000 iterations.
	}//end shuffle method
	
	public Card deal(){
			top++;
			return deck[top];
	}//returns the card on the top of the deck
}//end class