package base_jdk_new_jep.thread_virtual;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.junit.jupiter.api.Test;

public class VirtualThread01Test {

	// hread.startVirtualThread(() -> {...} 创建虚拟线程，并直接启动执行任务：
	@Test
	void test01() throws Exception {
		printMethodThreadId();
		// 传入Runnable实例并立刻运行:
		Thread vt = Thread.startVirtualThread(() ->
			{
				System.out.println("Start virtual thread...");
				long threadId1 = Thread.currentThread().threadId();
				System.out.println("threadId1=" + threadId1);// threadId=28

				sleep(10);
				System.out.println("End virtual thread.");
			});
	}

	// Thread.ofVirtual().unstarted(() -> {...} 只创建虚拟线程，但不直接启动（创建之后通过 start 启动）：
	@Test
	void test02() throws Exception {
		printMethodThreadId();
		// 创建VirtualThread:
		Thread vt = Thread.ofVirtual().unstarted(() ->
			{
				System.out.println("Start virtual thread...");
				sleep(1000);
				System.out.println("End virtual thread.");
			});
		// 运行:
		vt.start();
	}

	// 先创建虚拟线程工厂，然后再使用工厂创建虚拟线程，之后再调用 start() 方法进行执行：
	// ThreadFactory tf = Thread.ofVirtual().factory();
	// Thread vt = tf.newThread(() -> {...}
	// vt.start();
	@Test
	void test03() throws Exception {
		// 创建ThreadFactory:
		ThreadFactory tf = Thread.ofVirtual().factory();
		// final ThreadFactory factory = Thread.ofVirtual().name("worker-", 0).factory();
		// 创建VirtualThread:
		Thread vt = tf.newThread(() ->
			{
				System.out.println("Start virtual thread...");
				sleep(1000);
				System.out.println("End virtual thread.");
			});
		// 运行:
		vt.start();
	}

	// 创建虚拟线程池： Executors.newVirtualThreadPerTaskExecutor();
	@Test
	void test04() throws Exception {
		// 创建调度器:
		ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
		// 创建大量虚拟线程并调度:
		ThreadFactory tf = Thread.ofVirtual()//
				// .name("vt-")// String name，这个不是前缀，用这个，固定就是这个名字
				.name("vt-", 0)// String prefix, long start，用这个，第一个参数是前缀，第2个是以0开始
				// .inheritInheritableThreadLocals(false)//默认为true
				// .uncaughtExceptionHandler(null)//
				.factory();

		Runnable runuable = () ->
			{
				doTask();
			};
		Callable<Integer> callable = () ->
			{
				return doTask();
			};
		for (int i = 0; i < 100; i++) {
			Thread vt = tf.newThread(() ->
				{
					//
				});
			executor.submit(vt);
			// 也可以直接传入Runnable或Callable:
			executor.submit(() ->
				{
					String name = vt.getName();
					System.out.println("Start virtual thread..." + name);
					Thread.sleep(1000);
					System.out.println("End virtual thread.");
					return true;
				});
		}
	}

	private Integer doTask() {
		return 0;
	}

	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private final void printMethodThreadId() {
		long threadId0 = Thread.currentThread().threadId();
		System.out.println("threadId0=" + threadId0);// threadId0=1
	}

}
