package base_jdk_new_jep._record;

import java.util.Objects;

public record MyRecord(String str) {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyRecord m1 = new MyRecord("hello");
		MyRecord m2 = new MyRecord("hello");

		System.out.println(m1.hashCode());// 99162322
		System.out.println(m2.hashCode());// 99162322

		// identityHashCode代表的是内存地址（不直接等于，指的是，以内存算出来的hashcode，相等就说明内存地址一致，基本不等于hashcode）
		// identityHashCode相等的情况，也仅限于基本类型，且值在常量池，内存地址一致）
		System.out.println(System.identityHashCode(m1));// 2129789493,
		System.out.println(System.identityHashCode(m2));// 668386784

		System.out.println(Objects.hashCode(m1));// 99162322
		System.out.println(Objects.hashCode(m2));// 99162322
		System.out.println(m1.equals(m2));// true
		System.out.println(m1 == m2);// false
	}

	// @Override
	// public int hashCode() {
	// return new Random().nextInt();
	// //强制设这个了，hashcode会不一样，但是equals还是true，看样子，recode是个正常的类，有hashcode，只不过是值相等，equals就相当（因为自动按字段覆写了equals和hashcode）
	// //备注：
	// //1)（equals等，hashcode不一定等，但是建议等，so，只是建议，不是必须，反之亦然，建议而不是强关联）
	// //2）官方：覆写equals后，建议重写hashcode，没说覆写hashcode后，建议重写equals(没提，担也是建议，不过也没上来就直接覆写hashcode的，所以没必要提)
	// //return 0;
	// }

}
