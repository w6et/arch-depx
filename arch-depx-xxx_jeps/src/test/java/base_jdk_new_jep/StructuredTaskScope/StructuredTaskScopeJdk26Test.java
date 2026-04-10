package base_jdk_new_jep.StructuredTaskScope;

import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Joiner;
import java.util.concurrent.StructuredTaskScope.Subtask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomUtils;
//import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import base_jdk_new_jep.StructuredTaskScope.StructuredTaskScopeTest.TaskReq;

//import org.springframework.util.StringUtils;
// Unchecked
// Exception:NullPointerException\CompletionException\ConcurrentModificationException\DateTimeException\FileSystemAlreadyExistsException\
// FileSystemNotFoundException\IllegalArgumentException\IllegalStateException\IllformedLocaleException\IndexOutOfBoundsException\RejectedExecutionException
// SecurityException\TypeNotPresentException\UncheckedIOException\UndeclaredThrowableException\UnsupportedOperationException
// Checked
// Exception:IllegalAccessException\IOException\java.util.concurrent.ExecutionException\GeneralSecurityException\InterruptedException\ParseException
// PrivilegedActionException\URISyntaxException
public class StructuredTaskScopeJdk26Test {

	private static final Logger logger = LoggerFactory.getLogger(StructuredTaskScopeJdk26Test.class);
//	// private static final Supplier<ArrayList<String>> supplier = ArrayList::new;
	//StructuredTaskScope<T, R>,Subtask<T>\
	
	//anySuccessfulOrThrow\allSuccessfulOrThrow
	StructuredTaskScope<Void, Void> taskScope0 = StructuredTaskScope.open(Joiner.<Void>anySuccessfulOrThrow());
	StructuredTaskScope<TaskResp, TaskResp> taskScope1 = StructuredTaskScope.open(Joiner.<TaskResp>anySuccessfulOrThrow());
	//TaskReq
	StructuredTaskScope<TaskResp, List<TaskResp>> taskScope2 = StructuredTaskScope.open(Joiner.<TaskResp>allSuccessfulOrThrow());
	private static final AtomicInteger count = new AtomicInteger();
	private static final String LINE_SEPARATOR = System.lineSeparator();
	private static final boolean UCKEX_Y = true;
	private static final boolean UCKEX_N = false;
	private static final boolean CKEX_Y = true;
	private static final boolean CKEX_N = false;

	public static void main(String[] args) {
		StructuredTaskScope<TaskResp, List<TaskResp>> open = StructuredTaskScope.open(Joiner.<TaskResp>allSuccessfulOrThrow());
		Supplier<Boolean> yesSpl = () -> Boolean.TRUE;
		Supplier<Boolean> noSpl = () -> Boolean.FALSE;
		Supplier<Long> msSpl = System::currentTimeMillis;
		Supplier<Double> randomValue = () -> Math.random();
		Supplier<TaskReq> taskReqSpl = () -> TaskReq.of(100, 200, UCKEX_Y, CKEX_N);// lambada不能用var
		// Supplier<TaskReq> supplier = TaskReq::of(100, 200, UCKEX_Y, CKEX_N);编译异常
//		testAnySucc2(anySucc2.get(), null, null);
//		testAnySucc3(anyFail2.get(), null, null);
//		testAnySucc3(anyFail2.get(), null, null);
	}

	// 里面Subtask是并行的
	// StructuredTaskScope<T>()，任务都会执行，相当于invokeAll，okOnAllSucc。
	// StructuredTaskScope.ShutdownOnFailure()，（相当于invokeAll(),failOnAnyFail)/exit\quit\bye\close，koOnAnyKo，faOnAnyFa
	// StructuredTaskScope.ShutdownOnSuccess()，（相当于invokeAny(),okOnAnySucc)
	<T> T race(Collection<Callable<T>> tasks) throws InterruptedException {
		try (@SuppressWarnings("preview")
		var scope = StructuredTaskScope.open(Joiner.<T>anySuccessfulOrThrow())) {
			tasks.forEach(scope::fork);
			return scope.join();
		}
	}

	@SuppressWarnings("preview")
	<T> List<T> runConcurrently(Collection<Callable<T>> tasks) throws InterruptedException {
		try (@SuppressWarnings("preview")
		var scope = StructuredTaskScope.open(Joiner.<T>allSuccessfulOrThrow())) {
			tasks.forEach(scope::fork);
			return scope.join();
		}
	}

//	<T> List<T> allSuccessful(List<Callable<T>> tasks) throws InterruptedException {
//		try (var scope = StructuredTaskScope.open(new CollectingJoiner<T>())) {
//			tasks.forEach(scope::fork);
//			return scope.join().toList();
//		}
//	}

	@SuppressWarnings("preview")
	@Test
	void test_callables() throws Exception {
		List<Callable<String>> callables = new ArrayList<>();
		try (var scope = StructuredTaskScope.open()) {

			List<Subtask<String>> subtasks = callables.stream().map(scope::fork).toList();

			scope.join();
			//scope.fork(callable||runable)//
			//scope.isCancelled();//

			Map<Boolean, Set<Subtask<String>>> map = subtasks.stream()
					.collect(Collectors.partitioningBy(h -> h.state() == Subtask.State.SUCCESS, Collectors.toSet()));

		} // close
	}
//
//	// @RepeatedTest(3) // 这个是并发的,@RepeatedTest\@Test 2个注解同时用，执行会累加，
//	@Test
//	void test_anySucc_t1fail_t2ok() {
//		Instant deadline = Instant.now().plusMillis(1000);
//		TaskReq t1 = TaskReq.of(100, 200, UCKEX_Y, CKEX_N);
//		TaskReq t2 = TaskReq.of(100, 200, UCKEX_N, CKEX_N);
//		anySuccisSucc(deadline, t1, t2);
//	}
//
//	@Test
//	void test_anySucc_t1ok_t2fail() {
//		Instant deadline = Instant.now().plusMillis(1000);
//		TaskReq t1 = TaskReq.of(100, 200, UCKEX_N, CKEX_N);
//		TaskReq t2 = TaskReq.of(100, 200, UCKEX_Y, CKEX_N);
//		anySuccisSucc(deadline, t1, t2);
//	}
//
//	@Test
//	void test_anySucc_t1fail_t2fail() {
//		Instant deadline = Instant.now().plusMillis(1000);
//		TaskReq t1 = TaskReq.of(100, 200, UCKEX_Y, CKEX_N);
//		TaskReq t2 = TaskReq.of(100, 200, UCKEX_Y, CKEX_N);
//		anySuccisSucc(deadline, t1, t2);
//	}
//
//	@Test
//	void test_anySucc_timeout() {
//		Instant deadline = Instant.now().plusMillis(100);
//		TaskReq t1 = TaskReq.of(100, 200, UCKEX_Y, CKEX_N);
//		TaskReq t2 = TaskReq.of(100, 200, UCKEX_Y, CKEX_N);
//		anySuccisSucc(deadline, t1, t2);
//	}
//
//	// @RepeatedTest(3)
//	@Test
//	void test_anyFail_allOk() throws Exception {
//		Instant deadline = Instant.now().plusMillis(1500);
//		TaskReq t1 = TaskReq.of(100, 200, UCKEX_N, CKEX_N);
//		TaskReq t2 = TaskReq.of(100, 200, UCKEX_N, CKEX_N);
//		anyFailIsFail(deadline, t1, t2);
//	}
//
//	@Test
//	void test_anyFail_t1Err_t2Ok() throws Exception {
//		Instant deadline = Instant.now().plusMillis(1500);
//		TaskReq t1 = TaskReq.of(100, 200, UCKEX_Y, CKEX_N);
//		TaskReq t2 = TaskReq.of(100, 200, UCKEX_N, CKEX_N);
//		anyFailIsFail(deadline, t1, t2);
//	}
//
//	@Test
//	void test_anyFail_t1Ok_t2Err() throws Exception {
//		Instant deadline = Instant.now().plusMillis(1500);
//		TaskReq t1 = TaskReq.of(100, 200, UCKEX_N, CKEX_N);
//		TaskReq t2 = TaskReq.of(100, 200, UCKEX_Y, CKEX_N);
//		anyFailIsFail(deadline, t1, t2);
//	}

//	public void anySuccisSucc(Instant deadline, TaskReq t1, TaskReq t2) {
//		logger.info("###################################start");
//		try (var scope = anySucc.get()) {// 不是and，是任一个ok就行
//			Supplier<TaskResp> supplier1 = scope.fork(() -> fetch(t1));
//			Supplier<TaskResp> supplier2 = scope.fork(() -> fetch(t2));
//			try {
//				// scope.join();// 没这一行，fetch方法不执行，但是控制台打了into日志，后面没日志了，
//				scope.joinUntil(deadline);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			TaskResp result = scope.result(e -> new WebApplicationException(e));// allFailed会走到此，timeout也会走到此
//			// ...
//			log("##" + result.count + " result=" + result);
//
//			// IllegalStateException: Result is unavailable or subtask did not complete successfully，提示是下一行
////			List<TaskResp> list = Stream.of(supplier1, supplier2).map(Supplier::get).toList();//
////			for (TaskResp taskResp : list) {
////				logger.info("####" + "result.taskResp=" + taskResp);
////			}
//		} // 如果没join()，直接result()，会抛IllegalStateException: Owner did not join after forking subtasks，提示是这一行
//
//		logger.info("###################################end");// any 1个成功，就会执行到此，2个失败，则不会执行到此，scope.result()这一行抛异常了
//	}
//
//	public void anyFailIsFail(Instant deadline, TaskReq t1, TaskReq t2) {
//		try (var scope = anyFail.get()) {// 任一fail，就fail
//			Supplier<TaskResp> supplier1 = scope.fork(() -> fetch(t1));
//			Supplier<TaskResp> supplier2 = scope.fork(() -> fetch(t2));
//			try {
//				// scope.join();// 没这一行，fetch方法不执行，但是控制台打了into日志，后面没日志了，
//				scope.joinUntil(deadline);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			scope.throwIfFailed(e -> new WebApplicationException(e));// 任一fail，执行到这里
//			// 或者
//			scope.throwIfFailed(handleError("invalid error"));
//
//			// both subtasks completed successfully
//			// String result = Stream.of(supplier1, supplier2).map(Supplier::get).collect(Collectors.joining(", ", "{ ", " }"));
//
//			// ...
//		}
//	}
//
////	<T extends TaskReq2, R extends TaskResp> void testAnySucc(StructuredTaskScope<R> scope) {
////		Supplier<TaskResp> supplier1 = scope.fork(() -> fetch(TaskReq.of(100, 200, UCKEX_N, CKEX_N)));
////		Supplier<TaskResp> supplier2 = scope.fork(() -> fetch(TaskReq.of(100, 200, UCKEX_N, CKEX_N)));
////	}
//
//	<T extends TaskReq2, R extends TaskResp2<T>> void testAnySucc2(StructuredTaskScope<TaskResp2<T>> scope, T t1, T t2) {
//		// TaskResp2<T> fetch2 = fetch2(t1);
//		Supplier<TaskResp2<T>> supplier1 = scope.fork(() -> fetch2(t1));
//		Supplier<TaskResp2<T>> supplier2 = scope.fork(() -> fetch2(t2));
//	}
//
//	<T extends TaskReq2, R extends Object> void testJoiner(StructuredTaskScope<R> scope, T t1, T t2, Instant deadline) {
//		try {
//			Supplier<R> supplier1 = scope.fork(() -> fetch3(t1));
//			Supplier<R> supplier2 = scope.fork(() -> fetch3(t2));
//
//			// scope.join();// 没这一行，fetch方法不执行，但是控制台打了into日志，后面没日志了，
//			scope.joinUntil(deadline);
//			// supplier1.get();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	<T extends TaskReq, R extends TaskResp> void testAllSucc(StructuredTaskScope<Object> scope) {
//		Supplier<TaskResp> supplier1 = scope.fork(() -> fetch(TaskReq.of(100, 200, UCKEX_N, CKEX_N)));
//		Supplier<TaskResp> supplier2 = scope.fork(() -> fetch(TaskReq.of(100, 200, UCKEX_N, CKEX_N)));
//	}

//	<T extends TaskReq, R extends TaskResp> void test(StructuredTaskScope<R> scope, T t1, T t2) {
//		StructuredTaskScope<R> scope2 = null;
//		if (scope instanceof StructuredTaskScope.ShutdownOnSuccess<R> anySucc) {
//			scope2 = anySucc;
//		} else if (scope instanceof StructuredTaskScope.ShutdownOnFailure anyFail) {//编译错误
//			scope2 = (StructuredTaskScope<R>) anyFail;
//		}
//	}
//
//	<T extends TaskReq, R extends TaskResp> void test(StructuredTaskScope.ShutdownOnSuccess<R> scope, T t1, T t2) {
//		try {
//			// scope.fork(StructuredTaskScopeTest::fetch(t1));
//			Supplier<R> supplier1 = scope.fork(() -> fetch2(t1));
//			Supplier<R> supplier2 = scope.fork(() -> fetch2(t2));
//
//			// scope.join();// 没这一行，fetch方法不执行，但是控制台打了into日志，后面没日志了，
//			Instant deadline = Instant.now().plusMillis(120);
//			scope.joinUntil(deadline);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	// 为啥新定义了TaskReq2，不用TaskReq？因为record类型在泛型场景，不能直接调用里面的字段，必须换成类
	private static <T extends TaskReq2, R extends Object> R fetch3(T taskReq) throws Exception {
		int cnt = count.incrementAndGet();
		log("##" + cnt + " into......fetch()..." + LINE_SEPARATOR + "##taskReq=" + taskReq + "......" + LINE_SEPARATOR);
		int randInt = sleepRandInt(taskReq.sleepRandLf, taskReq.sleepRandRf);
		log("##" + cnt + "##randInt=" + randInt);
		if (taskReq.unCheckedEx) {
			throw new IllegalArgumentException("IllegalArgumentException");
		}
		if (taskReq.checkedEx) {
			throw new IllegalAccessException("IllegalAccessException");
		}
		TaskResp2<T> of = TaskResp2.of(taskReq, cnt, randInt);
		R r = (R) of;
		return r;// 编译异常
	}

	private static <T extends TaskReq2, R extends TaskResp2<T>> TaskResp2<T> fetch2(T taskReq) throws Exception {
		int cnt = count.incrementAndGet();
		log("##" + cnt + " into......fetch()..." + LINE_SEPARATOR + "##taskReq=" + taskReq + "......" + LINE_SEPARATOR);
		int randInt = sleepRandInt(taskReq.sleepRandLf, taskReq.sleepRandRf);
		log("##" + cnt + "##randInt=" + randInt);
		if (taskReq.unCheckedEx) {
			throw new IllegalArgumentException("IllegalArgumentException");
		}
		if (taskReq.checkedEx) {
			throw new IllegalAccessException("IllegalAccessException");
		}
		TaskResp2<T> of = TaskResp2.of(taskReq, cnt, randInt);
		return of;// 编译异常
	}

	private static TaskResp fetch(TaskReq taskReq) throws Exception {
		int cnt = count.incrementAndGet();
		log("##" + cnt + " into......fetch()..." + LINE_SEPARATOR + "##taskReq=" + taskReq + "......" + LINE_SEPARATOR);
		int randInt = sleepRandInt(taskReq.sleepRandLf, taskReq.sleepRandRf);
		log("##" + cnt + "##randInt=" + randInt);
		if (taskReq.unCheckedEx) {
			throw new IllegalArgumentException("IllegalArgumentException");
		}
		if (taskReq.checkedEx) {
			throw new IllegalAccessException("IllegalAccessException");
		}
		return TaskResp.of(taskReq, cnt, randInt);
	}

	static class TaskReq2 {
		int sleepRandLf;
		int sleepRandRf;
		boolean unCheckedEx;
		boolean checkedEx;

		static TaskReq2 of(int sleepRandLf, int sleepRandRf, boolean unCheckedEx, boolean checkedEx) {
			TaskReq2 taskResp2 = new TaskReq2();
			taskResp2.sleepRandLf = sleepRandLf;
			taskResp2.sleepRandRf = sleepRandRf;
			taskResp2.unCheckedEx = unCheckedEx;
			taskResp2.checkedEx = checkedEx;
			return taskResp2;
		}
	}

	static class TaskResp2<T extends TaskReq2> {
		T taskReq;
		int count;
		int randInt;

		static <T extends TaskReq2, R extends TaskResp2> TaskResp2<T> of(T taskReq, int count, int randInt) {
			TaskResp2<T> taskResp2 = new TaskResp2<T>();
			taskResp2.taskReq = taskReq;
			taskResp2.count = count;
			taskResp2.randInt = randInt;
			return taskResp2;
		}
	}

	record TaskReq(int sleepRandLf, int sleepRandRf, boolean unCheckedEx, boolean checkedEx) implements Serializable {
		static TaskReq of(int sleepRandLf, int sleepRandRf, boolean unCheckedEx, boolean checkedEx) {
			return new TaskReq(sleepRandLf, sleepRandRf, unCheckedEx, checkedEx);
		}

		public TaskReq {
			Objects.requireNonNull(sleepRandLf);
			Objects.requireNonNull(sleepRandRf);
			if (sleepRandRf < 10 || sleepRandRf < 10) {
				throw new IllegalArgumentException("sleepRandRf or sleepRandLf must >0");
			}
			if (sleepRandRf < sleepRandLf) {
				throw new IllegalArgumentException("sleepRandRf must gt sleepRandLf");
			}
		}
	}

	record TaskResp(TaskReq taskReq, int count, int randInt) {
		static TaskResp of(TaskReq taskReq, int count, int randInt) {
			return new TaskResp(taskReq, count, randInt);
		}
	}

	private static void log(String msg) {
		String separator = System.lineSeparator();
		logger.info(separator + "##msg=" + msg);
	}

	private static Integer fetchOrder() throws InterruptedException, ExecutionException {
		logger.info("##into......fetchOrder()...," + "......");
		int sleepRandInt = sleepRandInt(100, 3000);
		logger.info("##succ......fetchOrder()...,sleep=" + sleepRandInt);
		return sleepRandInt;
	}

	private static String findUser() throws InterruptedException, ExecutionException {
		logger.info("##into......findUser()...," + "......");
		int sleepRandInt = sleepRandInt(100, 3000);
		logger.info("##succ......findUser()...,sleep=" + sleepRandInt);
		return "str=".concat("").concat(",").concat("" + sleepRandInt).concat("ms");
	}

	private static class WebApplicationException extends RuntimeException {

		public WebApplicationException(Throwable cause) {
			// TODO Auto-generated method stub
		}

	}

	public Function<Throwable, IllegalArgumentException> handleError(String message) {
		return throwable ->
			{
				// log.error("do something here...");
				return new IllegalArgumentException(message);
			};
	}

	private static boolean randomBoolean() {
		RandomUtils randomUtils = RandomUtils.insecure();
		boolean randomBoolean = randomUtils.randomBoolean();
		return randomBoolean;
	}

	private static int sleepRandInt(final int startInclusive, final int endExclusive) throws InterruptedException, ExecutionException {
		RandomUtils randomUtils = RandomUtils.insecure();
		int nextInt = randomUtils.randomInt(startInclusive, endExclusive);
		Duration duration = Duration.ofMillis(nextInt);
		Thread.sleep(duration);
		return nextInt;

	}

//	@BeforeEach // 不能是static
//	public void test() {
//		System.out.println();
//	}

//	// Return the first successful result, cancel the rest
//	<T> T race(Collection<Callable<T>> tasks) throws InterruptedException {
//	    try (var scope = StructuredTaskScope.open(
//	            Joiner.<T>anySuccessfulResultOrThrow())) {
//	        tasks.forEach(scope::fork);
//	        return scope.join();
//	    }
//	}
//	
//	public interface Joiner<T, R> {
//	    public default boolean onFork(Subtask<? extends T> subtask);
//	    public default boolean onComplete(Subtask<? extends T> subtask);
//	    public R result() throws Throwable;
//	}

}
