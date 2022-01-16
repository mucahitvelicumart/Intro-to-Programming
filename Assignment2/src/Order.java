
public class Order {
	String order_name;
	int count_of_order=0;
	int item_count=0;
	public static Order load() {
		Order order=new Order();
		return order;
	}
	public static void get_order_status(Table[] array) {
		System.out.println("***********************************");
		System.out.println("PROGRESSING COMMAND: get_order_status");
		for(int i=0;i<Assignment2.sequence_of_table;i++) {
			System.out.println("Table: "+array[i].Id);
			System.out.println("	"+array[i].total_order+" order(s)");
			for(int j=0 ; j<array[i].total_order;j++) {
				System.out.println("		"+array[i].order.get(j).item_count+" item(s)");
			}
			
		}
	}
}
