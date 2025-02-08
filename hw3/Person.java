class Human{
	private int gender;
	private int age;
	private String origin;
	
	
	public Human() {
		
	}
	
	public Human(int gender,int age,String origin){
		this.gender=gender;
		this.age=age;
		this.origin=origin;
		
	}
	
	
	
	
}


class Employee extends Human{

	public Employee(int gender, int age, String origin) {
		super(gender, age, origin);
		// TODO Auto-generated constructor stub
	}
	
	
	
}


public class Person {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
}
