
public class Employer {
	String name;
	float salary;
	int number_of_table;
	public static Employer[] load() {
		Employer Employers[];
		Employers=new Employer[5];
		return Employers;
	}
	public static Employer[] adding_employer(Employer[] array,String name,float salary,int sequence) {
		array[sequence]=new Employer();
		array[sequence].name=name;
		array[sequence].salary=salary;
		array[sequence].number_of_table=0;
		return array;
	}
	public static Table[] creating_table(Table[] array,String name,int capacity,int sequence,Employer[] employer) {
		int control_name=-1;
		for(int i=0;i<Assignment2.sequence_of_employer;i++) {
			if(employer[i].name.equals(name) ) {
				control_name=i;
			}
		}
		if(control_name==-1) {
			System.out.println("***********************************");
			System.out.println("PROGRESSING COMMAND: create_table");
			System.out.println("There is no employer named "+name);
			
		}
		else if(Assignment2.sequence_of_table>4) {
			System.out.println("***********************************");
			System.out.println("PROGRESSING COMMAND: create_table");
			System.out.println("Not allowed to exceed max. number of tables, MAX_TABLES");
			
		}
		else if(employer[control_name].number_of_table>1) {
			System.out.println("***********************************");
			System.out.println("PROGRESSING COMMAND: create_table");
			System.out.println(name+" has already created ALLOWED_MAX_TABLES tables!" );
			
		}
		else {
			array[sequence]=new Table();
			array[sequence].name_of_creator=name;
			array[sequence].capacity=capacity;
			array[sequence].total_order=0;
			array[sequence].free_or_full=true;
			array[sequence].Id=sequence;
			employer[control_name].number_of_table++;
			Assignment2.total_table++;
			Assignment2.sequence_of_table++;
			System.out.println("***********************************");
			System.out.println("PROGRESSING COMMAND: create_table");
			System.out.println("A new table has successfully been added");
		}
		return array;
	}
	public static void get_employer_salary(Employer[] employer) {
		System.out.println("***********************************");
		System.out.println("PROGRESSING COMMAND: get_employer_salary");
		for(int i=0;i<Assignment2.sequence_of_employer;i++) {
			System.out.println("Salary for "+employer[i].name+": "+(employer[i].salary+(employer[i].salary*employer[i].number_of_table/10)));
		}
	}
}
