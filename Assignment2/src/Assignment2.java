import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment2 {
	static int sequence_of_table=0;
	static int sequence_of_item=0;
	static int sequence_of_employer=0;
	static int sequence_of_waiter=0;
	static int total_table=0;
	static ArrayList<Item> Items=new ArrayList<Item>();
	public static void main(String[] args) throws IOException {
		Employer[] Employers=Employer.load();
		Waiter[] Waiters=Waiter.load();
		Table[] Tables=Table.load();
		String setupFile="setup.dat";
		BufferedReader 	setup=new BufferedReader(new FileReader(setupFile));
		String lineOfSetupFile;
		while((lineOfSetupFile=setup.readLine())!=null	) {
			String[] splited=lineOfSetupFile.split(" ");
			if(splited[0].equals("add_item")) {
				String[] splited2=splited[1].split(";");
				Items=Item.load(Items, splited2[0], Float.parseFloat(splited2[1]),Integer.parseInt(splited2[2]) , sequence_of_item);
				sequence_of_item++;
			}
			else if(splited[0].equals("add_employer")) {
				if(sequence_of_employer>4) {
					System.out.println("MAX_EMPLOYER");
				}
				else {
					String[] splited2=splited[1].split(";");
					Employers=Employer.adding_employer(Employers, splited2[0], Float.parseFloat(splited2[1]), sequence_of_employer);
					sequence_of_employer++;
				}			
			}
			else if(splited[0].equals("add_waiter")){
				if(sequence_of_waiter>4) {
					System.out.println("MAX_WAITER");
				}
				else {
					String[] splited2=splited[1].split(";");
					Waiters=Waiter.adding_waiter(Waiters, splited2[0], Float.parseFloat(splited2[1]), sequence_of_waiter);
					sequence_of_waiter++;	
				}
			}
		}
		String commandFile="commands.dat";
		BufferedReader 	command=new BufferedReader(new FileReader(commandFile));
		String lineOfCommandFile;
		while((lineOfCommandFile=command.readLine())!=null	) {
			String[] splited_command=lineOfCommandFile.split(" ");
			if(splited_command[0].equals("create_table")){
				String[] splited_command2=splited_command[1].split(";");
				Tables=Employer.creating_table(Tables, splited_command2[0], Integer.parseInt(splited_command2[1]), sequence_of_table,Employers);
			}
			else if(splited_command[0].equals("new_order")) {
				String[] splited_command2=splited_command[1].split(";");
				Tables=Waiter.new_order(Tables, splited_command2[0], Integer.parseInt(splited_command2[1]), splited_command2[2], Waiters, Items);
			}
			else if(splited_command[0].equals("add_order")) {
				String[] splited_command2=splited_command[1].split(";");
				Tables=Waiter.add_order(Tables, splited_command2[0], Integer.parseInt(splited_command2[1]), splited_command2[2], Waiters, Items);
			}
			else if(splited_command[0].equals("get_table_status")) {
				Table.get_table_status(Tables);
		    }
			else if(lineOfCommandFile.equals("stock_status")) {
				Item.stock_status(Items);
			}
			else if(lineOfCommandFile.equals("get_order_status")) {
				Order.get_order_status(Tables);
			}
			else if(splited_command[0].equals("check_out")) {
				String[] splited_command2=splited_command[1].split(";");
				Tables=Waiter.check_out(Tables, splited_command2[0], Integer.parseInt(splited_command2[1]), Waiters, Items);
			}
			else if(lineOfCommandFile.equals("get_employer_salary")) {
				Employer.get_employer_salary(Employers);
			}
			else if(lineOfCommandFile.equals("get_waiter_salary")) {
				Waiter.get_waiter_salary(Waiters);
			}
	}
  }
}