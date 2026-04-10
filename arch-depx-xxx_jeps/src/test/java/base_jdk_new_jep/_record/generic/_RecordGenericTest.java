package base_jdk_new_jep._record.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

public class _RecordGenericTest {

	public static final String OKE = "oke";
	public static final String ERR = "err";

	@Test
	void test_PairRec() throws Exception {
		// 创建一个键值对，键为 String，值为 Integer
		PairRec<String, Integer> pair = new PairRec<>("Age", 30);

		// 访问键和值
		System.out.println("Key: " + pair.key());
		System.out.println("Value: " + pair.value());
	}

	@Test
	void test_ResponseRec() throws Exception {
		// 嵌套泛型：Response 包含一个 List 类型的数据
		RespRec<List<String>> response = new RespRec<>(List.of("Item1", "Item2"), OKE, "Success");

		System.out.println("Data: " + response.data());
		System.out.println("Message: " + response.message());
	}

	@Test
	void test_generic() throws Exception {
		PairRec<String, Integer> pair = new PairRec<>("Age", 30);
		// RespRec<List<String>> resp1 = new RespRec<>(List.of("Item1", "Item2"), OKE, "Success");
		RespRec<List<String>> resp1 = test2(pair);
		resp1.data();
		resp1.message();

		List<EleRec> eleRecList = new ArrayList<>();

		test1(null, null);
	}
//	<T extends PairRec<k, V>, R extends ResponseRec<Z>> R test(T t) {
//
//		return null;
//	}

	@SuppressWarnings("hiding")
	<K, V, E, D extends Collection<E>, T extends PairRec<K, V>, R extends RespRec<D>> RespRec<List<E>> test2(T t) {
		K k = t.key();
		V v = t.value();

		List<E> list = new ArrayList<>();
		// list.add(E);
		// r.data();
		// r.message();

		RespRec<List<E>> response = new RespRec<>(list, OKE, "Success");
		return response;
	}

	@SuppressWarnings("hiding")
	<M, E, D extends Collection<E>, T extends ReqRec, R extends RespRec<D>> RespRec<List<E>> test1(T req, D data) {
		var id = req.id();
		var input = req.input();

		List<E> list = new ArrayList<>();
		// list.add(E);
		// r.data();
		// r.message();

		RespRec<List<E>> response = new RespRec<>(list, OKE, "Success");
		return response;
	}
	
}
