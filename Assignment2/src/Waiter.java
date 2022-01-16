import java.util.ArrayList;

public class Waiter {
	String name;
	float salary;
	int number_of_service;
	int total_service;
	public static Waiter[] load() {
		Waiter Waiters[];
		Waiters=new Waiter[5];
		return Waiters;
	}
	public static Waiter[] adding_waiter(Waiter[] array,String name,float salary,int sequence) {
		array[sequence]=new Waiter();
		array[sequence].name=name;
		array[sequence].salary=salary;
		array[sequence].number_of_service=0;
		array[sequence].total_service=0;
		return array;
	}
	public static Table[] new_order(Table[] array,String name,int capacity,String given_orders,Waiter[] waiters,ArrayList<Item> items) {
		int control_of_waiter=-1;
		int control_of_capacity=-1;
		for(int j=0;j<Assignment2.sequence_of_table;j++) {
			if(control_of_capacity!=-1) {
				continue;
			}
			else if((capacity<=array[j].capacity)&& (array[j].free_or_full==true)) {
				control_of_capacity=j;
			}
		}
		for(int i=0;i<Assignment2.sequence_of_waiter;i++) {
			if(waiters[i].name.equals(name)) {
				control_of_waiter=i;
			}
		}
		if(control_of_waiter==-1) {
			System.out.println("***********************************");
			System.out.println("PROGRESSING COMMAND: new_order");
			System.out.println("There is no waiter named kemal");	
		}
		else if(control_of_capacity==-1) {
			System.out.println("***********************************");
			System.out.println("PROGRESSING COMMAND: new_order");
			System.out.println("There is no appropriate table for this order!");
		}
		else if(waiters[control_of_waiter].number_of_service>1) {
			System.out.println("***********************************");
			System.out.println("PROGRESSING COMMAND: new_order");
			System.out.println("Not allowed to service max. number of tables, MAX_TABLE_SERVICES");
		}
		else {
			System.out.println("***********************************");
			System.out.println("PROGRESSING COMMAND: new_order");
			System.out.println("Table (= ID "+array[control_of_capacity].Id+") has been taken into service");
			int service_control=-1;
			for(int j=0;j<waiters.length;j++) {
				if(service_control!=-1) {
					continue;
				}
				else if(waiters[j].name.equals(name)){
					service_control=j;
				}
			}
			waiters[service_control].number_of_service++;
			waiters[service_control].total_service++;
			array[control_of_capacity].name_of_waiterer=name;
			array[control_of_capacity].total_order++;
			array[control_of_capacity].free_or_full=false;
			int gecici_order=0;
			String[] split1=given_orders.split(":");
			for(int i=0;i<split1.length;i++) {
				String[] split2=split1[i].split("-");
				int stok_control=-1;
				for(int k=0;k<(Assignment2.Items.size());k++) {
					if(stok_control!=-1) {
						continue;
					}
					else if(Assignment2.Items.get(k).name.equals(split2[0])) {
						stok_control=k;
					}
				}
				if(stok_control==-1) {
					System.out.println("Unknown item Waffle");
				}
				else {
					array[control_of_capacity].order.add(new Order());
					array[control_of_capacity].order.get(i).order_name=split2[0];
					array[control_of_capacity].free_or_full=false;
					for(int j=0;j<(Integer.parseInt(split2[1]));j++) {
						if(Assignment2.Items.get(stok_control).stock==0) {
							System.out.println("Sorry! No "+split2[0]+" in the stock!");
						}
						else if(gecici_order>9) {
							System.out.println("Up to 10 items can be ordered in one order.");
						}
						else {
							System.out.println("Item "+split2[0]+" added into order");
							Assignment2.Items.get(stok_control).stock-=1;
							array[control_of_capacity].order.get(i).count_of_order+=1;
							gecici_order++;
						}
					}
				}
				array[control_of_capacity].order.get(0).item_count=gecici_order;
			}
		}
		return array;
	}
	public static Table[] add_order(Table[] array,String name,int ID,String given_orders,Waiter[] waiters,ArrayList<Item> items) {
		int control_waiter=-1;
		System.out.println("***********************************");
		System.out.println("PROGRESSING COMMAND: add_order");
		for(int i=0;i<Assignment2.sequence_of_waiter;i++) {
			if(control_waiter !=-1) {
				continue;
			}
			else if(waiters[i].name.equals(name)){
				control_waiter=i;
			}
		}
		if(!(array[ID].name_of_waiterer.equals(name))) {
			System.out.println("This table is either not in service now or "+name+" cannot be assigned this table!");
		}
		else if(control_waiter==-1) {
			System.out.println("There is no waiter named "+name);
		}
		else {
			if(array[ID].total_order>4) {
				System.out.println("Not allowed to exceed max number of orders!");
			}
			else {
				waiters[control_waiter].total_service++;
				array[ID].total_order++;
				int gecici_order=0;
				String[] split1=given_orders.split(":");
				for(int c=0;c<split1.length;c++) {
					String[] split2=split1[c].split("-");
					int control_exist_order=-1;
					int stok_control=-1;
					for(int j=0;j<array[ID].order.size();j++) {
						if(control_exist_order!=-1) {
							continue;
						}
						else if(array[ID].order.get(j).order_name.equals(split2[0])) {
							control_exist_order=j;
						}
					}
					for(int k=0;k<(Assignment2.Items.size());k++) {
						if(stok_control!=-1) {
							continue;
						}
						else if(Assignment2.Items.get(k).name.equals(split2[0])) {
							stok_control=k;
						}
					}
					if(control_exist_order== -1) {
						array[ID].order.add(new Order());
						for(int j=0;j<Integer.parseInt(split2[1]);j++) {
							if(stok_control==-1) {
								System.out.println("Unknown item "+name);
							}
							else if(Assignment2.Items.get(stok_control).stock==0) {
								System.out.println("Sorry! No "+split2[0]+" in the stock!");
							}
							else if(gecici_order>9) {
								System.out.println("Up to 10 items can be ordered in one order.");
							}
							else {
								array[ID].order.get(array[ID].order.size()-1).order_name=split2[0];
								System.out.println("Item "+split2[0]+" added into order");
								Assignment2.Items.get(stok_control).stock-=1;
								array[ID].order.get(array[ID].order.size()-1).count_of_order++;
								gecici_order++;
							}	
						}	
					
					}
					else if(control_exist_order!=-1) {
						for(int j=0;j<Integer.parseInt(split2[1]);j++) {
							if(stok_control==-1) {
								System.out.println("Unknown item "+name);
							}
							if(Assignment2.Items.get(stok_control).stock==0) {
								System.out.println("Sorry! No "+split2[0]+" in the stock!");
							}
							else if(gecici_order>9) {
								System.out.println("Up to 10 items can be ordered in one order.");
							}
							else {
								System.out.println("Item "+split2[0]+" added into order");
								Assignment2.Items.get(stok_control).stock-=1;
								array[ID].order.get(control_exist_order).count_of_order+=1;
								gecici_order++;
							}
						}
					}
					
				}
				array[ID].order.get(array[ID].total_order-1).item_count=gecici_order;
			}
		}
		return array;
	}
	public static Table[] check_out(Table[] array,String name,int ID,Waiter[] waiters,ArrayList<Item> items) {
		System.out.println("***********************************");
		System.out.println("PROGRESSING COMMAND: check_out");
		int control_waiter=-1;
		for(int i=0;i<Assignment2.sequence_of_waiter;i++) {
			if(control_waiter!=-1) {
				continue;
			}
			else if(waiters[i].name.equals(name)) {
				control_waiter=i;
			}
		}
		if(control_waiter==-1) {
			System.out.println("There is no waiter named "+name);
		}
		else if(!(array[ID].name_of_waiterer.equals(name))) {
			System.out.println("This table is either not in service now or "+name+" cannot be assigned this table!");
		}
		else {
			float total=0;
			for(int i=0;i<array[ID].order.size();i++) {
				float gecici_cost=0;
				for(int j=0;j<items.size();j++) {
					if(array[ID].order.get(i).order_name.equals(items.get(j).name)) {
						gecici_cost=items.get(j).cost;
					}
					else {
						continue;
					}
				}
				
				System.out.printf(array[ID].order.get(i).order_name+":	"+(String.format("%.3f",gecici_cost ))+" (x "+array[ID].order.get(i).count_of_order+") "+(String.format("%.3f", gecici_cost*array[ID].order.get(i).count_of_order))+" $\n");
				total+=(gecici_cost*array[ID].order.get(i).count_of_order);
			}
			System.out.printf("Total:	"+String.format("%.3f", total)+" $\n");
			waiters[control_waiter].number_of_service-=1;
			array[ID].total_order=0;
			array[ID].free_or_full=true;
			array[ID].order.clear();
		}
		return array;
	}
	public static void get_waiter_salary(Waiter[] waiters) {
		System.out.println("***********************************");
		System.out.println("PROGRESSING COMMAND: get_waiter_salary");
		for(int i=0;i<Assignment2.sequence_of_waiter;i++) {
			System.out.println("Salary for "+waiters[i].name+": "+(waiters[i].salary+(waiters[i].salary*waiters[i].total_service/20)));
		}
	}
}
