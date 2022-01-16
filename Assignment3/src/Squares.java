import java.io.PrintWriter;

public class Squares  {
	int ID;
	String name;
	public Squares(int iD, String name) {
		ID = iD;
		this.name = name;
	}
	public static int Rent(Squares[] array,int dice,People playingplayer,People otherplayer) {
		int rent;
		
		if (array[((Players) playingplayer).location] instanceof Lands) {
			if (((Lands)array[((Players) playingplayer).location]).cost>3000) {
				rent= (((Lands)array[((Players) playingplayer).location]).cost/100)*35;
			}
			else if (((Lands)array[((Players) playingplayer).location]).cost>2000) {
				rent= (((Lands)array[((Players) playingplayer).location]).cost/100)*30;
			}
			else {
				rent= ((((Lands)array[((Players) playingplayer).location]).cost)/100)*40;
			}
		}
		else if (array[((Players) playingplayer).location] instanceof Railroads) {
			rent=((Players) otherplayer).num_of_railroad*25;
		}
		else if (array[((Players) playingplayer).location] instanceof Companies) {
			rent = 25*dice;
		}
		else {
			rent=0;
		}
		return rent;
	}


	public void buy(Squares[] squares,Players playingplayer,boolean cardMove,People[] people, PrintWriter output,Players otherplayer) {
		
	}
}
