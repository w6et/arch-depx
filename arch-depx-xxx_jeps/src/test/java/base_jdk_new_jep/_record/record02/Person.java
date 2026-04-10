package base_jdk_new_jep._record.record02;

import java.time.LocalDate;
import java.util.Objects;

import net.bytebuddy.asm.Advice.This;

public record Person(String name, LocalDate birthday, String city) {

	public static String UNKNOWN_ADDRESS = "Unknown";
	static int age = 10;// 不能定义全局，只能加static，但是加static，等于外部能改变这个值，应该有个构造函数变量，或者能用final定义即可，
	// private final int age2;
	public Person {
		Objects.requireNonNull(name, "name is required");
		Objects.requireNonNull(birthday, "birthday is required");
		Objects.requireNonNull(city, "city is required");
		age = 1;
		// if (age < 0) {
		// throw new IllegalArgumentException("Age cannot be negative");
		// }
	}

	public static Person of() {
		return new Person(null, null, null);
	}

	public static Person of(String name, LocalDate birthday, String city) {
		return new Person(name, birthday, city);
	}

	// 添加其他方法
	public void greet() {
		System.out.println("Hello, my name is " + name + " and I am " + age + " years old.");
	}

}
