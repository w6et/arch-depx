package base_jdk_new_jep.var_handle;

import java.util.Arrays;

public class Demo {

	public int count = 1;
	protected long sum = 100;
	private String name = "init";
	public int[] arrayData1 = new int[] { 3, 5, 7 };
	private int[] arrayData2 = new int[] { 2, 4, 8 };

	@Override
	public String toString() {
		return "Demo{" + "name='" + name + '\'' + ", count=" + count + ", sum=" + sum + ", data="
				+ Arrays.toString(arrayData1) + ','
				+ Arrays.toString(arrayData2) +'}';
	}
}
