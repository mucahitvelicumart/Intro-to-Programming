import java.util.ArrayList;

public class Table {
	String name_of_creator;
	String name_of_waiterer;
	int Id;
	int capacity;
	int total_order;
	ArrayList<Order> order=new ArrayList<Order>();
	boolean free_or_full;
	public static Table[] load() {
		Table Tables[];
		Tables=new Table[5];
		return Tables;
	}
	public static void get_table_status(Table[] array) {
		System.out.println("***********************************");
		System.out.println("PROGRESSING COMMAND: get_table_status");
		for(int i=0;i<Assignment2.sequence_of_table;i++) {
			if(array[i].free_or_full==true) {
				System.out.println("Table "+array[i].Id+": Free");
			}
			else if(array[i].free_or_full==false){
				System.out.println("Table "+array[i].Id+": Reserved ("+array[i].name_of_waiterer+")");
			}
		}
	}
}
