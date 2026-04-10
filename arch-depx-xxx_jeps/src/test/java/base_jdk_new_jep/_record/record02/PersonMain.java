package base_jdk_new_jep._record.record02;

import java.time.LocalDate;

public class PersonMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person.age=5;
		System.out.println(Person.age);//5
		
		Person p1=Person.of("name11", LocalDate.now(), "city11");
		System.out.println(p1);//_Person2[name=name11, birthday=2025-01-12, city=city11]
		System.out.println(p1.name());
		System.out.println(p1.birthday());
		System.out.println(p1.city());
	}

}
