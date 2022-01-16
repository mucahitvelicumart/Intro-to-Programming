import java.io.PrintWriter;

public class Action extends Squares {
	
	public Action(int iD, String name) {
		super(iD, name);
	}
	public static void communityActions(People playingplayer,People otherplayer,Cards[] array,People banker,int dice,People[] people,boolean doubleCard,PrintWriter output) {
		if(array[0].item.equals("Advance to Go (Collect $200)")) {
			((Players) playingplayer).location=0;
			((Players) playingplayer).money+=200;
			if (doubleCard==true) {
				System.out.println(playingplayer.name+" draw community chest-Advance to Go (Collect $200) ");
				output.println(playingplayer.name+" draw community chest-Advance to Go (Collect $200) ");
			}
			else {
				Players.gaming(playingplayer, otherplayer, dice, people,output);
				System.out.println("draw community chest-advance to go");
				output.println("draw community chest-advance to go");
			}
		
			banker.money-=200;
		}
		else if(array[0].item.equals("Bank error in your favor - collect $75")) {
			
			if (doubleCard==true) {
				System.out.println(playingplayer.name+" draw community chest-Bank error in your favor - collect $75");	
				output.println(playingplayer.name+" draw community chest-Bank error in your favor - collect $75");
			}
			else {
				Players.gaming(playingplayer, otherplayer, dice, people,output);
				System.out.println("draw community chest-Bank error in your favor - collect $75");
				output.println("draw community chest-Bank error in your favor - collect $75");
			}
			
			((Players) playingplayer).money+=75;
			banker.money-=75;
		}
		else if(array[0].item.equals("Doctor's fees - Pay $50")) {
			if (doubleCard==true) {
				System.out.println(playingplayer.name+" draw community chest- Doctor's fees - Pay $50");
				output.println(playingplayer.name+" draw community chest- Doctor's fees - Pay $50");
			}
			else {
				Players.gaming(playingplayer, otherplayer, dice, people,output);
				System.out.println("draw community chest- Doctor's fees - Pay $50");
				output.println("draw community chest- Doctor's fees - Pay $50");
			}
			
			((Players) playingplayer).money-=50;
			banker.money+=50;
		}
		else if(array[0].item.equals("It is your birthday Collect $10 from each player")) {
			if (doubleCard==true) {
				System.out.println(playingplayer.name+" draw community chest-It is your birthday Collect $10 from each player");
				output.println(playingplayer.name+" draw community chest-It is your birthday Collect $10 from each player");
				
			}
			else {
				Players.gaming(playingplayer, otherplayer, dice, people,output);
				System.out.println("draw community chest-It is your birthday Collect $10 from each player");
				output.println("draw community chest-It is your birthday Collect $10 from each player");
			}
			
			((Players) otherplayer).money-=10;
			((Players) playingplayer).money+=10;
			
		}
		else if(array[0].item.equals("Grand Opera Night - collect $50 from every player for opening night seats")) {
			if (doubleCard==true) {
				System.out.println(playingplayer.name+" draw community chest -Grand Opera Night - collect $50 from every player for opening night seats");
				output.println(playingplayer.name+" draw community chest -Grand Opera Night - collect $50 from every player for opening night seats");
			}
			else {
				Players.gaming(playingplayer, otherplayer, dice, people,output);
				System.out.println("draw community chest -Grand Opera Night - collect $50 from every player for opening night seats");
				output.println("draw community chest -Grand Opera Night - collect $50 from every player for opening night seats");
			}
			
			((Players) otherplayer).money-=50;
			((Players) playingplayer).money+=50;
		}
		else if(array[0].item.equals("Income Tax refund - collect $20")) {
			if (doubleCard==true) {
				System.out.println(playingplayer.name+" draw community chest- Income Tax refund - collect $20");
				output.println(playingplayer.name+" draw community chest- Income Tax refund - collect $20");
				
			}
			else {
				Players.gaming(playingplayer, otherplayer, dice, people,output);
				System.out.println("draw community chest- Income Tax refund - collect $20");
				output.println("draw community chest- Income Tax refund - collect $20");
			}
			
			((Players) playingplayer).money+=20;
			banker.money-=20;
		}
		else if(array[0].item.equals("Life Insurance Matures - collect $100")) {
			if (doubleCard==true) {
				System.out.println(playingplayer.name+" draw community chest- Life Insurance Matures - collect $100");
				output.println(playingplayer.name+" draw community chest- Life Insurance Matures - collect $100");
			}
			else {
				Players.gaming(playingplayer, otherplayer, dice, people,output);
				System.out.println("draw community chest- Life Insurance Matures - collect $100");
				output.println("draw community chest- Life Insurance Matures - collect $100");
			}
			
			((Players) playingplayer).money+=100;
			banker.money-=100;
		}
		else if(array[0].item.equals("Pay Hospital Fees of $100")) {
			if (doubleCard==true) {
				System.out.println(playingplayer.name+" draw community chest -Pay Hospital Fees of $100");
				output.println(playingplayer.name+" draw community chest -Pay Hospital Fees of $100");
			}
			else {
				Players.gaming(playingplayer, otherplayer, dice, people,output);
				System.out.println("draw community chest -Pay Hospital Fees of $100");
				output.println("draw community chest -Pay Hospital Fees of $100");
			}
			
			((Players) playingplayer).money-=100;
			banker.money+=100;
		}
		else if(array[0].item.equals("Pay School Fees of $50")) {
			if (doubleCard==true) {
				System.out.println(playingplayer.name+" draw community chest- Pay School Fees of $50");
				output.println(playingplayer.name+" draw community chest- Pay School Fees of $50");
			}
			else {
				Players.gaming(playingplayer, otherplayer, dice, people,output);
				System.out.println("draw community chest- Pay School Fees of $50");
				output.println("draw community chest- Pay School Fees of $50");
			}
			
			((Players) playingplayer).money-=50;
			banker.money+=50;
		}
		else if(array[0].item.equals("You inherit $100")) {
			if (doubleCard==true) {
				System.out.println(playingplayer.name+" draw community chest- You inherit $100");
				output.println(playingplayer.name+" draw community chest- You inherit $100");
			}
			else {
				Players.gaming(playingplayer, otherplayer, dice, people,output);
				System.out.println("draw community chest- You inherit $100");
				output.println("draw community chest- You inherit $100");
			}
			
			((Players) playingplayer).money+=100;
			banker.money-=100;
		}
		else if(array[0].item.equals("From sale of stock you get $50")) {
			if (doubleCard==true) {
				System.out.println(playingplayer.name+" draw community chest- From sale of stock you get $50");
				output.println(playingplayer.name+" draw community chest- From sale of stock you get $50");
				
			}
			else {
				Players.gaming(playingplayer, otherplayer, dice, people,output);
				System.out.println("draw community chest- From sale of stock you get $50");
				output.println("draw community chest- From sale of stock you get $50");
			}
			
			((Players) playingplayer).money+=50;
			banker.money-=50;
		}
		
	}
	public static boolean chanceActions(People playingplayer, People otherplayer, Cards[] array, People banker,int dice,People[] people,boolean cardMove,PrintWriter output) {
			
		if (array[0].item.equals("Advance to Go (Collect $200)")) {
			((Players) playingplayer).location=0;
			((Players) playingplayer).money+=200;
			Players.gaming(playingplayer, otherplayer, dice, people,output);
			System.out.println("draw chance card - Advance to Go (Collect $200) ");
			output.println("draw chance card - Advance to Go (Collect $200) ");
			banker.money-=200;
		}
		else if (array[0].item.equals("Advance to Leicester Square")) {
			((Players) playingplayer).location=26;
			Players.gaming(playingplayer, otherplayer, dice, people,output);
			System.out.printf("draw chance card-Advance to Leicester Square ");
			output.printf("draw chance card-Advance to Leicester Square ");
			cardMove = true;
			
		}
		else if(array[0].item.equals("Go back 3 spaces")) {
			((Players) playingplayer).location-=3;
			Players.gaming(playingplayer, otherplayer, dice, people,output);
			System.out.printf("draw chance card-Go back 3 spaces ");
			output.printf("draw chance card-Go back 3 spaces ");
			cardMove = true;
			
		}
		else if(array[0].item.equals("Pay poor tax of $15")) {
			((Players) playingplayer).money-=15;
			banker.money+=15;
			Players.gaming(playingplayer, otherplayer, dice, people,output);
			System.out.println("draw chance card-Pay poor tax of $15");
			output.println("draw chance card-Pay poor tax of $15");
			
		}
		else if(array[0].item.equals("Your building loan matures - collect $150")) {
			((Players) playingplayer).money+=150;
			banker.money-=150;
			Players.gaming(playingplayer, otherplayer, dice, people,output);
			System.out.println("draw chance card-Your building loan matures - collect $150");
			output.println("draw chance card-Your building loan matures - collect $150");
			
		}
		else if(array[0].item.equals("You have won a crossword competition - collect $100")) {
			((Players) playingplayer).money+=100;
			banker.money-=100;
			Players.gaming(playingplayer, otherplayer, dice, people,output);
			System.out.println("draw chance card-You have won a crossword competition - collect $100");
			output.println("draw chance card-You have won a crossword competition - collect $100");
			
		}
		return cardMove;
	}
	public static Cards[] underDeck(Cards[] array) {
		Cards temp = array[0];
		for (int i = 1; i < array.length; i++) {
			array[i-1]=array[i];
		}
		array[array.length-1]=temp;
		return array;
	}
}
