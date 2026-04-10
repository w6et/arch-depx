package base_jdk_new_jep._record;

public record Point(int x, int y) {

	public Point {//构造方法
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException();
		}
	}

	public static Point of() {
		return new Point(0, 0);
	}

	public static Point of(int x, int y) {
		return new Point(x, y);
	}

	public void test() {

	}
}