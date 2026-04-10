package base_jdk_new_jep;

import static java.lang.IO.println;
import static java.lang.System.console;
import static java.io.Console.*;
import module java.base;

public class StdoutTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IO.println();
		println();
		console();
		Console console = System.console();
		if (console != null) {
			char[] password = console.readPassword("请输入密码: ");
			// 处理密码
		}
		if (console != null) {
			console.printf("当前时间: %tT%n", new Date());
		}
		if (console != null) {
			String input;
			while ((input = console.readLine("请输入命令: ")) != null) {
				if (input.equalsIgnoreCase("exit")) {
					break;
				}
				console.printf("执行命令: %s%n", input);
			}
		}

		String username = console.readLine("User name: ");
		char[] passwd = console.readPassword("PassWord: ");
		// console.printf(username, args)
		System.out.println("User name: " + username);
		System.out.println("PassWord: " + String.valueOf(passwd));

		if (console != null) {
			Scanner scanner = new Scanner(console.reader());
			// 使用scanner进行输入解析
		}
	}

}
