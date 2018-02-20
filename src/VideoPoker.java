//*****************************************
//VideoPoker.java
//
//by Stefanie Molin
//November 1, 2011
//*****************************************
import java.util.Collections;
import java.util.Scanner;

public class VideoPoker {
	private Deck d=new Deck();
	private Player p=new Player();
	private boolean royalStraightFlush;
	private boolean straightFlush;
	private boolean fourOfAKind;
	private boolean fullHouse;
	private boolean flush;
	private boolean straight;
	private boolean threeOfAKind;
	private boolean twoPair;
	private boolean pair;

	public void play(){
		Scanner input=new Scanner(System.in);
		String again="Play Again";
		System.out.println("\nWelcome to Stefanie's Video Poker!\n");
		while(!again.equalsIgnoreCase("Quit")){
			d.shuffle();//shuffles the deck
			for(int i=0; i<5; i++){
				p.hand.add(d.deal());
			}//deals player a five card hand
	
			Collections.sort(p.hand);//sorts the player's hand
			System.out.println("The deck has been shuffled; here is your hand. (I was nice enough to sort it for you!)\n");
			System.out.println(p.hand);//shows the player his/her sorted hand
			System.out.println("\nWhich cards would you like to discard? (input 5 values with spaces in between)\n0=discard & any other value/character=keep (i.e. 1 0 0 1 0)");//asks player which cards to discard
			p.discard();
			for(int i=0; i<5; i++){
				if(p.reject.get(i).equals("0")){
					p.hand.remove(i);
					p.hand.add(i, d.deal());
				}
			}//removes selected cards and replaces them from the top of the deck
	
			Collections.sort(p.hand);//sorts player's new hand
			System.out.println("Here is your final hand (sorted for you). \n" + p.hand);//prints final hand
			System.out.println("\nYour superior poker abilities earned you a...");
			rankHand();//tells player their hand
			
			//clear hand and reset booleans for next game
			p.hand.clear();
			pair=false;
			twoPair=false;
			threeOfAKind=false;
			straight=false;
			flush=false;
			fullHouse=false;
			fourOfAKind=false;
			straightFlush=false;
			royalStraightFlush=false;
			
			System.out.println("\nWould you like to play again?\n Type \"Yes\" to play again. Type \"Quit\" to exit (without quotes).\n");
			again=input.next();
			while(!(again.equalsIgnoreCase("Quit")||again.equalsIgnoreCase("Yes"))){
				System.out.println("\nType \"Yes\" to continue. \n Type \"Quit\" to exit.");
				again=input.next();	
			}//prompts user to type quit or yes (ignoring case) until the user inputs it properly to determine whether or not to play again.
		}
		System.out.println("\nThank you for playing!");
		input.close();
	}
	public void rankHand(){
		//checks hands in order of hierarchy and returns the best hand
		if(hasRoyalStraightFlush()){
			System.out.println("Royal Straight Flush");	
		}
		else if(hasStraightFlush()){
			System.out.println("Straight Flush");
		}
		else if(hasFourOfAKind()){
			System.out.println("Four of a Kind");
		}
		else if(hasFullHouse()){
			System.out.println("Full House");
		}	
		else if(hasFlush()){
			System.out.println("Flush");
		}
		else if(hasStraight()){
			System.out.println("Straight");
		}
		else if(hasThreeOfAKind()){
			System.out.println("Three of a Kind");
		}
		else if(hasTwoPair()){
			System.out.println("Two Pair");
		}
		else if(hasPair()){
			System.out.println("Pair");
		}
		else{
			System.out.println("No Pair");
		}
	}
	
	private boolean hasPair(){
		//since cards are sorted, only adjacent cards can have the same value, this method ensures only 1 pair
		if(p.hand.get(0).value==p.hand.get(1).value && p.hand.get(1).value!=p.hand.get(2).value){
			pair=true;
		}
		else if(p.hand.get(1).value==p.hand.get(2).value && p.hand.get(2).value!=p.hand.get(3).value && p.hand.get(2).value!=p.hand.get(0).value){
			pair=true;
		}
		else if(p.hand.get(2).value==p.hand.get(3).value && p.hand.get(3).value!=p.hand.get(4).value){
			pair=true;
		}
		else if(p.hand.get(3).value==p.hand.get(4).value){
			pair=true;
		}
		else
			pair=false;
		return pair;
	}
	
	private boolean hasTwoPair() {
		//checks for two pairs
		int numPairs=0;
		for(int i=1; i<5; i++){
			if(p.hand.get(i).value==p.hand.get(i-1).value){
				numPairs++;
			}
		}
		if(numPairs==2){
			twoPair=true;
		}
		return twoPair;
	}

	private boolean hasThreeOfAKind() {
		//checks for three equal value cards, since they are sorted by value a three card set will have two equal value cards on the outsides implying the inner is equal
		if(p.hand.get(0).value==p.hand.get(2).value || p.hand.get(1).value==p.hand.get(3).value || p.hand.get(2).value==p.hand.get(4).value){
			threeOfAKind=true;
		}
		return threeOfAKind;
	}

	private boolean hasStraight() {	
		//checks for a straight by ensuring the cards are consecutive
		int matches=0;
		for(int i=1; i<5; i++){
			if(p.hand.get(i).value-1==p.hand.get(i-1).value){
				matches++;
			}
		}
		if(matches==4){
			straight=true;	
		}
		if(p.hand.get(4).value==13 && p.hand.get(3).value==12 && p.hand.get(2).value==11 && p.hand.get(1).value==10 && p.hand.get(0).value==1){
			straight=true;//ace can be counted two ways to get a straight
		}
		return straight;
	}

	private boolean hasFlush() {
		//checks that all cards have the same suit
		int matches=0;
		for(int i=1; i<5; i++){
			if(p.hand.get(0).suit==p.hand.get(i).suit){
				matches++;
			}
		}
		if(matches==4){
			flush=true;
		}
		return flush;
	}

	private boolean hasFullHouse() {
		//full house has a three of a kind and a pair so both must be true
		if((p.hand.get(0).value==p.hand.get(2).value && p.hand.get(3).value == p.hand.get(4).value) 
				|| (p.hand.get(2).value==p.hand.get(4).value && p.hand.get(0).value == p.hand.get(1).value)){
			fullHouse=true;
		}
		return fullHouse;
	}

	private boolean hasFourOfAKind() {
		//if 4 cards have equal value the outer 2 being equal imply the inner two are (since they are sorted)
		if(p.hand.get(0).value==p.hand.get(3).value || p.hand.get(1).value==p.hand.get(4).value){
			fourOfAKind=true;
		}
		return fourOfAKind;
	}

	private boolean hasStraightFlush() {
		//straight and flush must both be true
		if(hasFlush() && hasStraight()){
			straightFlush=true;
		}
		return straightFlush;
	}

	private boolean hasRoyalStraightFlush() {
		if(hasFlush() && hasStraight() && p.hand.get(0).value==1 && p.hand.get(4).value==13){
			//check for ace and king, if they are there and has straight it must be A,K,Q,J,10 and flush completes the test
			royalStraightFlush=true;
		}
		return royalStraightFlush;
	}
}//end class
