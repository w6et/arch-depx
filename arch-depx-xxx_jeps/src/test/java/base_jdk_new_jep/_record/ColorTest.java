package base_jdk_new_jep._record;

public class ColorTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		var orange = new Color(237, 139, 0);
		var blue = new Color(0, 115, 150);
		
		System.out.println("##" + "orange" + "=" + orange + "##");//##orange=Color[red=-19, green=-117, blue=0]##
		System.out.println("##" + "blue" + "=" + blue + "##");//##blue=Color[red=0, green=115, blue=-106]##

		var orange2 = new Color(237, 139, 0);

		boolean orangeIsSameRef = (orange == orange2);
		System.out.println("##" + "orangeIsSameRef" + "=" + orangeIsSameRef + "##");// false

		boolean orangeEq = orange.equals(orange2);// ##recEq=true##
		System.out.println("##" + "orangeEq" + "=" + orangeEq + "##");// true

	}

}
