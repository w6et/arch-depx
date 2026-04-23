package base_jdk_new_jep.uuidv7;

import static java.lang.IO.*;

import java.util.UUID;

public class Uuidv7Test {

	public static void main(String[] args) {
		UUID uuidv7 = UUID.ofEpochMillis(System.currentTimeMillis());
		// ##uuid.v7=019dab87-bb88-7b4b-8a5a-5099683b3625##
		printUuid(uuidv7);

		UUID uuidv4 = UUID.randomUUID();
		// ##uuid.v4=655d2b37-9330-49cf-9cd0-082fef7b739d##
		printUuid(uuidv4);

		// UUID.nameUUIDFromBytes(null);// NPE
		UUID uuidv3 = UUID.nameUUIDFromBytes("".getBytes());
		// ##uuid.v3=d41d8cd9-8f00-3204-a980-0998ecf8427e##
		printUuid(uuidv3);

		// parseUuidStr(null);// 解析异常，不能null
		// parseUuidStr("");// Invalid UUID string:
		// parseUuidStr("23423423423");// Invalid UUID string: 23423423423，必须有效，不是随便
		parseUuidStr("019dab87-bb88-7b4b-8a5a-5099683b3625");
		parseUuidStr("655d2b37-9330-49cf-9cd0-082fef7b739d");
		parseUuidStr("d41d8cd9-8f00-3204-a980-0998ecf8427e");
	}

	private static void printUuid(UUID uuid) {
		// println("##" + "uuid" + "=" + uuid + "##");
		int version = uuid.version();
		println("##" + "v" + version);
		println("##" + "uuid" + "=" + uuid + "##");
		int variant = uuid.variant();// v7、v4、v3，variant都是2
		println("##" + "uuid.variant" + "=" + variant + "##");
		try {
			long timestamp = uuid.timestamp();// UnsupportedOperationException: Not a time-based UUID
			println("##" + "uuid.timestamp" + "=" + timestamp + "##");
		} catch (Exception e) {
			// v7、v4、v3，都打印：##uuid.timestamp=Not a time-based UUID##
			println("##" + "uuid.timestamp" + "=" + e.getMessage() + "##");
		}
		println();
	}

	private static void parseUuidStr(String uuidStr) {
		try {
			UUID uuid = UUID.fromString(uuidStr);
			println("UUID: " + "解析成功" + "：" + uuid + ";v" + uuid.version());
		} catch (Exception e) {
			println("UUID: " + "解析异常" + "：" + e.getMessage());
		}
	}

}
