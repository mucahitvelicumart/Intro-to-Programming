import java.io.PrintWriter;
import java.util.ArrayList;

public class Players extends People{
	int location;
	boolean jail;
	int jail_count;
	ArrayList<String> properties;
	int num_of_railroad;
	public Players(String name, int money,int Location,boolean Jail,int Jail_count) {
		super(name, money);
		this.jail = Jail;
		this.jail_count = Jail_count;
		this.location = Location;
		this.properties = new ArrayList<>();
	}
	public static void gaming(People playingplayer,People otherplayer,int dice,People[] array,PrintWriter output ) {
		System.out.printf(((Players) playingplayer).name+"	"+dice+"	"+(((Players) playingplayer).location+1)+"	"+((Players)array[0]).money+"	"+((Players)array[1]).money+"	"+((Players) playingplayer).name+" ");
		output.printf(((Players) playingplayer).name+"	"+dice+"	"+(((Players) playingplayer).location+1)+"	"+((Players)array[0]).money+"	"+((Players)array[1]).money+"	"+((Players) playingplayer).name+" ");
	}
	
}
