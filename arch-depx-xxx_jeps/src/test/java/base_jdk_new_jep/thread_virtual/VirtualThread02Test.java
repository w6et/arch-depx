package base_jdk_new_jep.thread_virtual;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.junit.jupiter.api.Test;

public class VirtualThread02Test {

	// hread.startVirtualThread(() -> {...} 创建虚拟线程，并直接启动执行任务：
	@Test
	void test01() throws Exception {
		//先tf=Thread.ofVirtual().factory();再tf.newThread看起来还不如如下：
		Thread.Builder builder = Thread.ofVirtual().name("worker-", 0);
		Runnable task = () ->
			{
				System.out.println("Thread ID: " + Thread.currentThread().threadId());
			};

		// name "worker-0"
		Thread t1 = builder.start(task);
		t1.join();
		System.out.println(t1.getName() + " terminated");

		// name "worker-1"
		Thread t2 = builder.start(task);
		t2.join();
		System.out.println(t2.getName() + " terminated");
	}

	// Thread.ofVirtual().unstarted(() -> {...} 只创建虚拟线程，但不直接启动（创建之后通过 start 启动）：

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
