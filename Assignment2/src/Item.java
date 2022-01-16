import java.util.ArrayList;

public class Item {
	String name;
	float cost;
	int stock;
	public static ArrayList<Item> load(ArrayList<Item> array,String name,float cost,int number,int sequence){
		array.add(new Item());
		array.get(sequence).name=name;
		array.get(sequence).cost=cost;
		array.get(sequence).stock=number;
		return array;
	}
	public static void stock_status(ArrayList<Item> items) {
		System.out.println("***********************************");
		System.out.println("PROGRESSING COMMAND: stock_status");
		for(int i=0;i<items.size();i++) {
			
			System.out.printf(items.get(i).name+":	"+items.get(i).stock+"\n");
		}
	}

}
