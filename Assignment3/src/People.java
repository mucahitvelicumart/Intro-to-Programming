
import java.io.PrintWriter;

public class People {
	String name;
	int money;
	public People(String name, int money) {
		this.name = name;
		this.money = money;
	}
	public static People findPerson(People[] array, String name) {
		for (int i = 0; i < array.length; i++) {
			if (array[i].name.equals(name)) {
				return array[i];
			}
		}
		return null;
	}
	public static People FindPerson(People[] array, String name) {
		for (int i = 0; i < array.length; i++) {
			if (!(array[i].name.equals(name))) {
				return array[i];	
			}		
		}
		return null;
	}
	public static void Show(People[] array,PrintWriter output) {
		String winner;
		System.out.println("----------------------------------------------------------------------------------------------------------");
		output.println("----------------------------------------------------------------------------------------------------------");
		if(array[0].money<array[1].money) {
			winner= array[1].name;
		}
		else if(array[0].money>array[1].money){
			winner=array[0].name;
		}
		else {
			winner="Scoreless";
		}
		for (int i = 0; i < array.length-1; i++) {
			System.out.printf(((Players)array[i]).name+"	"+((Players)array[i]).money+"	have: ");
			output.printf(((Players)array[i]).name+"	"+((Players)array[i]).money+"	have: ");
			if (((Players)array[i]).properties.size()>0) {
				for (int j = 0; j < ((Players)array[i]).properties.size()-1; j++) {
					System.out.printf(((Players)array[i]).properties.get(j)+",");
					output.printf(((Players)array[i]).properties.get(j)+",");
				}
				System.out.println(((Players)array[i]).properties.get(((Players)array[i]).properties.size()-1));
				output.println(((Players)array[i]).properties.get(((Players)array[i]).properties.size()-1));
			}
			else {
				System.out.println();
				output.println();
			}
			
				
			}
		System.out.println(array[2].name+"	"+array[2].money);
		output.println(array[2].name+"	"+array[2].money);
		if (winner.equals("Scoreless")) {
			System.out.println(winner);
			output.println(winner);
		}
		else {
			System.out.println("Winner "+winner);
			output.println("Winner "+winner);
		}
		System.out.println("----------------------------------------------------------------------------------------------------------");
		output.println("----------------------------------------------------------------------------------------------------------");
		
	}
}
	