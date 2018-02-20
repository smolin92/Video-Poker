
//*****************************************
//Player.java
//
//by Stefanie Molin
//November 1, 2011
//*****************************************

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	public ArrayList<Card> hand=new ArrayList<Card>();
	public ArrayList<String> reject=new ArrayList<String>();

	@SuppressWarnings("resource")
	public void discard(){
		Scanner input=new Scanner(System.in);
		for(int i=0; i<5; i++){
			reject.add(i, input.next());
		}//creates an array based on user input to determine which cards to discard
	}
}
